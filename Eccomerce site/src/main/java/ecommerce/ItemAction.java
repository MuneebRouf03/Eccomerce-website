package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemAction extends BaseAction
{
    
    // These save the data from the form when a user adds a new item
    private String itemName;      // Name of the item
    private String description;   // Description of the item
    private String price;         // Price of the item
    
    // This list storess all items for display on the page
    private List<Item> items;
    
    //  class to hold item information
    // 1. Item name
    // 2. Item description
    // 3. Item price
    // 4. Who is selling it
    public class Item {
        public String itemName;      // What the item is called
        public String description;   // What the item is
        public String price;         // How much it costs
        public String seller;        // Who is selling it
        
        // Getter methods to access item information on the web page
        public String getItemName() 
        { return itemName; }
        public String getDescription() 
        { return description; }
        public String getPrice() 
        { return price; }
        public String getSeller() 
        { return seller; }
    }
    
    // This method is called when a user wants to add a new item for sale
    public String addItem()
    {
        // First check if user is logged in (only logged in users can add items)
        if (!isLoggedIn())
        {
            addActionError("Please login first");
            return ERROR;
        }
        
        try {
            // Get a connection to the database sql 33-06
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // Get the username from the session (who is logged in and adding the item)
            String user = (String) session.get("loggedInUser");
            
            // Create SQL query to insert the new item into the database
            // It finds the user ID from the username, then adds the item with that seller ID
            String sql = "INSERT INTO items (item_name, description, price, seller_id) " +
                        "VALUES ('" + itemName + "', '" + description + "', " + 
                        price + ", (SELECT user_id FROM users WHERE username='" + user + "'))";
            stmt.executeUpdate(sql);
            
            // After adding the item, show all items (including the new one)
            return viewAllItems();
            
        } catch (Exception e) 
        {
            // If something goes wrong, show error message
            addActionError("Cannot add item");
            return ERROR;
        }
    }
    
    // This method shows all items available for sale on the website
    public String viewAllItems() {
        try {
            // Connect to the database
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // SQL query to get all items with their seller information
            // Joins items table with users table to get the seller's username
            String sql = "SELECT i.item_name, i.description, i.price, u.username " +
                        "FROM items i JOIN users u ON i.seller_id = u.user_id";
            ResultSet rs = stmt.executeQuery(sql);
            
            // Create an empty list to hold all items
            items = new ArrayList<>();
            
            // Loop through each row in the result and create Item objects
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
            // If something goes wrong, show error message
            addActionError("Cannot load items");
            return ERROR;
        }
    }
    
    // Getters and setters - these allow the framework to set values from forms
    // and get values for display on web pages
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public List<Item> getItems() { return items; }
}