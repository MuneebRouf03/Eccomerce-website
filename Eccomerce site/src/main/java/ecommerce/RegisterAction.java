package ecommerce;

import java.sql.Connection;
import java.sql.Statement;

public class RegisterAction extends BaseAction
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;  // Required for Struts2 serialization (keeps track of versions)
	private String username;  // Username entered  during registration
    private String password;  // Passwordd entered during registration
    
    // This method is automatically called when the registration form is submitted
    public String execute() {
        Connection conn = null;
        try {
            // Get a connection to the database 3306 port from my sql
            conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // Create SQL query to insert new user into the database
            // Takes the username and password and adds them to the users table
            String sql = "INSERT INTO users (username, password) VALUES ('" + 
                         username + "', '" + password + "')";
            stmt.executeUpdate(sql);  // Execute the insert query
            
            // Show success message to the person
            addActionMessage("Registration successful!");
            return SUCCESS;  // Registration successful
            
        } catch (Exception e) {
            // If something goes wrong (like duplicate username or database error), show error
            addActionError("Registration failed");
            return ERROR;  // Registration failed
        }
    }
    
    // Getters and setters - these allow the framework to get values from the registration form
    // and set them into these variables
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}