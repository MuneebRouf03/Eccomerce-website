package ecommerce;

import java.sql.Connection; //thiss imports the Connection class from the java.sql package
import java.sql.DriverManager;//Imports the DriverManager class from the java.sql package
import java.util.Map; //bascially imports the map  package so it keeps session data saved
import com.opensymphony.xwork2.ActionSupport;//Imports the ActionSupport class from the com.opensymphony.xwork2 package
import org.apache.struts2.interceptor.SessionAware; //Imports the SessionAware interface from the org.apache.struts2.interceptor package

public class BaseAction extends ActionSupport implements SessionAware
{
    
    // This is where we check if session data is saved (such as the person ID, name, and all)
    protected Map<String, Object> session;
    
    // This method creates a connection to the MySQL database, it connect the sql table to this action class
    protected Connection getConnection() throws Exception 
    {
        
    	// Load the MySQL Database Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Connect to the database using the URL, username and password
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ecommerce_db",  // port 3306 from my sql , where databse is
            "root",   // ← YOUR MYSQL USERNAME
            "root");  // ← YOUR MYSQL PASSWORD
    }
    
    // This method checks if a person is logged in in-session, by searching for "loggedInUser" in the session
    protected boolean isLoggedIn() 
    {
        return session.get("loggedInUser") != null;
    }
    
    // This method is automatically invoked by our Struts2 to provide us with the session object
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}