package ecommerce;

public class LogoutAction extends BaseAction 
{
    //logouts if the person decides so, u get confrimation on screen when user is logged out
    public String execute() {
        session.clear(); // Simple logout
        return SUCCESS;
    }
}