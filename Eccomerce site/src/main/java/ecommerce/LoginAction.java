package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginAction extends BaseAction {
    
    // These store the data that come from the login form
    private String username;  // Username that is entered by the user
    private String password;  // Password entered by the user
    
    // This method is automatically called when the login form is submitted
    public String execute() {
        Connection conn = null;
        try {
            // Get a connection to the database sql 3306
            conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // Create SQL query to check if username and password exist in database, store all the data from a user
            // This is checking if there's a user with this exact username and password
            String sql = "SELECT * FROM users WHERE username='" + 
                        username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            // If the query found at least one row, login is successful
            if (rs.next()) {
                // Store the username in the session to remember the user is logged in
                session.put("loggedInUser", username);
                return SUCCESS;  // Login successful
            } else {
                // If no matching user found, show error
                addActionError("Invalid login");
                return ERROR;  // Login failed
            }
            
        } catch (Exception e) {
            // If something goes wrong (like database problem), show error
            addActionError("Login error");
            return ERROR;
        }
    }
    
    // Getters and setters - these allow the framework to get values from the login form
    // and set them into these variables
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}