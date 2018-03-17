package listener;

import model.Appello;
import model.User;
import model.UserMgmt;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMgmtContextListener implements ServletContextListener {

    public UserMgmtContextListener() {
    }

    public void contextDestroyed(ServletContextEvent contextEvent)  {
    }

    public void contextInitialized(ServletContextEvent contextEvent)  {
    	ServletContext context = contextEvent.getServletContext();
    	UserMgmt um = new UserMgmt();
        context.setAttribute("users", um);
        ArrayList<Appello> appelli = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("/home/orange/Scaricati/a.txt"));

            Appello a = Appello.read(sc);
            while (a!=null){
                appelli.add(a);
                a = Appello.read(sc);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.setAttribute("appelli", appelli);
    }
	
}
