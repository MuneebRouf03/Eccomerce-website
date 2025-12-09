package ecommerce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAction extends BaseAction
{
    
    // This list will store all people o the screen
    private List<User> users;
    

    // This just stores the username for each user
    public class User 
    {
        public String username;  // Username of the user
        
        // Getter method to access username on the web page
        public String getUsername() 
        { return username; }
    }
    
    // This method is automatically called when viewing users
    public String execute()
    {
        try {
            // Get a connection to the database sql
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            
            // Create SQL query to get all usernames from the database
            // This selects all usernames from the users table
            String sql = "SELECT username FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            
            // Create an empty list to hold all users
            users = new ArrayList<>();
            
            // Loop through each row in the result and create User objects
            while (rs.next()) 
            {
                User user = new User();
                user.username = rs.getString("username");
                users.add(user);  // Add each user to the list
            }
            
            return SUCCESS;  // Show the users page
            
        } catch (Exception e) {
            // If something goes wrong (like database problem), show error
            addActionError("Cannot load users");
            return ERROR;
        }
    }
    
    // Getter method - allows the web page to access the list of users
    public List<User> getUsers() 
    { return users; }
}