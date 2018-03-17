package controller;

import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RmExam extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RmExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int i = Integer.parseInt(request.getParameter("rm"))-1;
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		Student s = (Student) session.getAttribute("user");
		s.rmExam(i);
		request.getRequestDispatcher("/student/Exams").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
