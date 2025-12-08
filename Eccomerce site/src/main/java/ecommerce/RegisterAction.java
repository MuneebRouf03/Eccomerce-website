package ecommerce;

import java.sql.Connection;
import java.sql.Statement;

public class RegisterAction extends BaseAction {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
    private String password;
    
    public String execute() {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // Simple insert - no duplicate checking
            String sql = "INSERT INTO users (username, password) VALUES ('" + 
                         username + "', '" + password + "')";
            stmt.executeUpdate(sql);
            
            addActionMessage("Registration successful!");
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Registration failed");
            return ERROR;
        }
    }
    
    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}