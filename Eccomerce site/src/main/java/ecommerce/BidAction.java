package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BidAction extends BaseAction
{ 
    // It saves the info of the data when a person makes a bidd
    private String itemName;
    private String bidAmount;
    
    // A list of all the bids wil be shows on the person screen
    private List<Bid> bids;
    
    // keep bid information of a person such user id and everything else
    // 1. What item is being bid on
    // 2. Who is making the bid
    // 3. How much they're bidding 
    
    public class Bid 
    { 
        public String itemName;
        public String bidder;
        public String amount;
             
        // Getter methods to read bid information
        public String getItemName() 
        { return itemName; }
        public String getBidder() 
        { return bidder; }
        public String getAmount() 
        { return amount; }
    }
    
    // This method is called when a someone requests a bid. 
    public String makeBid() 
    { 
        // First checks if a person is logged in. 
        if (!isLoggedIn()) 
        { 
            addActionError("Please login first");
            return ERROR;
        } 
        try 
        { 
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            String user = (String) session.get("loggedInUser");

            // Generate SQL query to insert the bid into the database. 
            // It selects the item ID via the item name, logs the user ID based on the username. 
            // Then adds the bid amount plus these IDs.  basically all the infos
            String sql = "INSERT INTO bids (item_id, bidder_id, bid_amount) " +
                         "VALUES ((SELECT item_id FROM items WHERE item_name='" + itemName + "'), " +
                         "(SELECT user_id FROM users WHERE username='" + user + "'), " + bidAmount + ")";
            stmt.executeUpdate(sql);
                             
            // Display the outputs from the person by inputting success message. 
            addActionMessage("Bid placed!");
            return SUCCESS;
                                 
        } catch (Exception e) 
        { 
            // If something goes wrong, it show error. 
            addActionError("Cannot place bid");
            return ERROR;
        } 
    }
    
    // This method displays all bids of the currently logged in user. 
    public String viewMyBids() 
    { 
        // Check if user is logged in. 
        if (!isLoggedIn()) 
        { 
            addActionError("Please login first");
            return ERROR;
        } 
        try 
        { 
            // Connect to database.3306  
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            // Getting username of current user. 
            String user = (String) session.get("loggedInUser");

            // SQL query for all requests made by this user to get all bids. 
            // It is joined into three tables based on the request which are bids and items and users. 
            // For item names and bid amounts. 
            String sql = "SELECT i.item_name, u.username, b.bid_amount " +
                         "FROM bids b " +
                         "JOIN items i ON b.item_id = i.item_id " +
                         "JOIN users u ON b.bidder_id = u.user_id " +
                         "WHERE u.username='" + user + "'";
            // Run the query and obtain output. 
            ResultSet rs = stmt.executeQuery(sql);
            bids = new ArrayList<>();
            
            // Return each of the row in the result with a unique Bid object. 
            while (rs.next()) 
            { 
                Bid bid = new Bid();
                bid.itemName = rs.getString("item_name");
                bid.bidder = rs.getString("username");
                bid.amount = rs.getString("bid_amount");
                bids.add(bid);
            } 
            return SUCCESS;
            
        } catch (Exception e) 
        { 
            addActionError("Cannot load bids");
            return ERROR;
        } 
    }
    
    // getters and setters -- these  set values from the forms. 
    // and get values for display on web pages. 
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