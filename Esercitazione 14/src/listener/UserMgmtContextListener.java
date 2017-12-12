package listener;

import model.UserMgmt;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserMgmtContextListener implements ServletContextListener {

    public UserMgmtContextListener() {
    }

    public void contextDestroyed(ServletContextEvent contextEvent)  {
    }

    public void contextInitialized(ServletContextEvent contextEvent)  {
    	ServletContext context = contextEvent.getServletContext();
    	UserMgmt um = new UserMgmt();
    	context.setAttribute("users", um);
    }
	
}
