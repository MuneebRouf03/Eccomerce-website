package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginAction extends BaseAction 
{
    private String username;
    private String password;
    
    public String execute() {
        Connection conn = null;
        try {
            conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // Simple query
            String sql = "SELECT * FROM users WHERE username='" + 
                        username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                // Store in session
                session.put("loggedInUser", username);
                return SUCCESS;
            } else {
                addActionError("Invalid login");
                return ERROR;
            }
            
        } catch (Exception e) {
            addActionError("Login error");
            return ERROR;
        }
    }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}