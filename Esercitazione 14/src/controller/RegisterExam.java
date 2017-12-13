package controller;

import model.Exam;
import model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterExam() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date d;
		try {
			d = new SimpleDateFormat("dd-MM-yyyy").parse(request.getParameter("examDate"));
			
			Exam ex = new Exam(request.getParameter("courseName"), request.getParameter("teacherName"), d);
			HttpSession session = request.getSession();
			Student s = (Student) session.getAttribute("user");
			s.setExam(ex);
			response.setContentType("text/html");
			RequestDispatcher rd = request.getRequestDispatcher("/restricted/Exams");
			rd.forward(request, response);
		} catch(Exception e) { e.printStackTrace();}
	}

}
