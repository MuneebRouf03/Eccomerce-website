package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemAction extends BaseAction {
    
    // For adding item
    private String itemName;
    private String description;
    private String price;
    
    // For viewing items
    private List<Item> items;
    
    // Simple item class
    public class Item {
        public String itemName;
        public String description;
        public String price;
        public String seller;
        
        // Getters
        public String getItemName() 
        { return itemName; }
        public String getDescription() 
        { return description; }
        public String getPrice() 
        { return price; }
        public String getSeller() 
        { return seller; }
    }
    
    // Add item
    public String addItem()
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
            String sql = "INSERT INTO items (item_name, description, price, seller_id) " +
                        "VALUES ('" + itemName + "', '" + description + "', " + 
                        price + ", (SELECT user_id FROM users WHERE username='" + user + "'))";
            stmt.executeUpdate(sql);
            
            return viewAllItems();
            
        } catch (Exception e) 
        {
            addActionError("Cannot add item");
            return ERROR;
        }
    }
    
    // View all items
    public String viewAllItems() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT i.item_name, i.description, i.price, u.username " +
                        "FROM items i JOIN users u ON i.seller_id = u.user_id";
            ResultSet rs = stmt.executeQuery(sql);
            
            items = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item();
                item.itemName = rs.getString("item_name");
                item.description = rs.getString("description");
                item.price = rs.getString("price");
                item.seller = rs.getString("username");
                items.add(item);
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Cannot load items");
            return ERROR;
        }
    }
    
    // Getters and setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public List<Item> getItems() { return items; }
}