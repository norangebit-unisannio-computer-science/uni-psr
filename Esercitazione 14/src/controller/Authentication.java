package controller;

import model.User;
import model.UserMgmt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Authentication() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserMgmt users = (UserMgmt) getServletConfig().getServletContext().getAttribute("users");
		User u = users.findUser(request.getParameter("username"));
		String url;
		if ((u != null) && (u.getPassword()==request.getParameter("password").hashCode())) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(120);
			session.setAttribute("user", u);
			System.out.println("in authentication " + session);
			url = "Menu.html";
		}
		else url = "NotAuthenticated.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
