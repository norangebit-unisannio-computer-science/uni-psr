package controller;

import model.Exam;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditExam extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int i = Integer.parseInt(request.getParameter("edit"))-1;
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		Student s = (Student) session.getAttribute("user");
		request.setAttribute("exam", s.getExam(i));
		s.rmExam(i);
		request.getRequestDispatcher("/EditExam.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
