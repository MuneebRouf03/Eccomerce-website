package ecommerce;

public class LogoutAction extends BaseAction 
{
    
    public String execute() {
        session.clear(); // Simple logout
        return SUCCESS;
    }
}