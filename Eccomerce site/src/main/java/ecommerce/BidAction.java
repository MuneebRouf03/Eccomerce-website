package ecommerce;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemAction extends BaseAction {
    private String itemName;
    private String description;
    private double price;
    private List<Item> items;
    
    // Inner class to represent an item
    public class Item {
        private int itemId;
        private String itemName;
        private String description;
        private double price;
        private String sellerName;
        
        // Getters and setters
        public int getItemId() { return itemId; }
        public void setItemId(int itemId) { this.itemId = itemId; }
        public String getItemName() { return itemName; }
        public void setItemName(String itemName) { this.itemName = itemName; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public String getSellerName() { return sellerName; }
        public void setSellerName(String sellerName) { this.sellerName = sellerName; }
    }
    
    public String addItem() {
        if (!isLoggedIn()) {
            addActionError("You must be logged in to add items");
            return INPUT;
        }
        
        Connection conn = null;
        try {
            conn = getConnection();
            Integer userId = (Integer) session.get("userId");
            
            String sql = "INSERT INTO items (item_name, description, price, seller_id) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemName);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setInt(4, userId);
            
            stmt.executeUpdate();
            addActionMessage("Item added successfully!");
            return viewAllItems();
            
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Failed to add item: " + e.getMessage());
            return INPUT;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
    
    public String viewAllItems() {
        Connection conn = null;
        try {
            conn = getConnection();
            
            String sql = "SELECT i.*, u.username as seller_name FROM items i " +
                         "JOIN users u ON i.seller_id = u.user_id ORDER BY i.created_at DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            items = new ArrayList<>();
            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setItemName(rs.getString("item_name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getDouble("price"));
                item.setSellerName(rs.getString("seller_name"));
                items.add(item);
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Failed to load items: " + e.getMessage());
            return INPUT;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }
    
    // Getters and setters
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}