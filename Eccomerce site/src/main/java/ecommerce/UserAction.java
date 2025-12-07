package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAction extends BaseAction
{
    
    private List<User> users;
    
    // Simple user class
    public class User {
        public String username;
        
        public String getUsername() { return username; }
    }
    
    public String execute()
    {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT username FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            
            users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.username = rs.getString("username");
                users.add(user);
            }
            
            return SUCCESS;
            
        } catch (Exception e) {
            addActionError("Cannot load users");
            return ERROR;
        }
    }
    
    public List<User> getUsers() 
    { return users; }
}