package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BidAction extends BaseAction
{
    private String itemName;
    private String bidAmount;
    
    // For viewing bids
    private List<Bid> bids;
    
    // Simple bid class
    public class Bid {
        public String itemName;
        public String bidder;
        public String amount;
        
       
        public String getItemName() 
        { return itemName; }
      
        public String getBidder()
        { return bidder; }
      
        public String getAmount() 
        { return amount; }
    }
    
    // Make a bid
    public String makeBid()
    {
        
    	if (!isLoggedIn())
    	{
            addActionError("Please login first");
            return ERROR;
        }
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            String user = (String) session.get("loggedInUser");
            
            // Simple bid insertion
            String sql = "INSERT INTO bids (item_id, bidder_id, bid_amount) " +
                         "VALUES ((SELECT item_id FROM items WHERE item_name='" + itemName + "'), " +
                         "(SELECT user_id FROM users WHERE username='" + user + "'), " + bidAmount + ")";
            stmt.executeUpdate(sql);
            
            addActionMessage("Bid placed!");
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Cannot place bid");
            return ERROR;
        }
    }
    
    // View my bids
    public String viewMyBids() {
        if (!isLoggedIn()) {
            addActionError("Please login first");
            return ERROR;
        }
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            String user = (String) session.get("loggedInUser");
            String sql = "SELECT i.item_name, u.username, b.bid_amount " +
                         "FROM bids b " +
                         "JOIN items i ON b.item_id = i.item_id " +
                         "JOIN users u ON b.bidder_id = u.user_id " +
                         "WHERE u.username='" + user + "'";
            
            ResultSet rs = stmt.executeQuery(sql);
            bids = new ArrayList<>();
            
            while (rs.next()) {
                Bid bid = new Bid();
                bid.itemName = rs.getString("item_name");
                bid.bidder = rs.getString("username");
                bid.amount = rs.getString("bid_amount");
                bids.add(bid);
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Cannot load bids");
            return ERROR;
        }
    }
    
    // Getters and setters
    public String getItemName() 
    { return itemName; }
   
    public void setItemName(String itemName)
    { this.itemName = itemName; }
    
    public String getBidAmount() 
    { return bidAmount; }
    
    public void setBidAmount(String bidAmount) 
    { this.bidAmount = bidAmount; }
    
    public List<Bid> getBids() 
    { return bids; }
}