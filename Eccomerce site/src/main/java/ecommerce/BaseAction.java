package ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

public class BaseAction extends ActionSupport implements SessionAware {
    
    protected Map<String, Object> session;
    
    // Simple database connection
    protected Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
        	    "jdbc:mysql://localhost:3306/ecommerce_db",  // ← DATABASE LOCATION
        	    "root",   // ← YOUR MYSQL USERNAME
        	    "root");  // ← YOUR MYSQL PASSWORD
    }
    
    // Check if user is logged in
    protected boolean isLoggedIn() {
        return session.get("loggedInUser") != null;
    }
    
    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}