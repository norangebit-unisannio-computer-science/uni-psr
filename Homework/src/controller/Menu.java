package controller;

import model.Appello;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Menu() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		ArrayList<Appello> appelli = (ArrayList<Appello>)getServletConfig().getServletContext().getAttribute("appelli");
		request.setAttribute("appelli", appelli);
		request.getRequestDispatcher("/Menu.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
