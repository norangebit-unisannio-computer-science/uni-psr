package ex2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Servlet implementation class Authentication
 */
@WebServlet("/authentication")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentication() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/plain");
		if (check(request.getParameter("email"), request.getParameter("password"))) {
			Cookie c = new Cookie("authToken", "0192837465");
			c.setMaxAge(120);
			response.addCookie(c);
			pw.println("Autenticazione effettuata con successo");
		} else {
			pw.println("Autenticazione fallita");
		}
		pw.flush();

	}

	private Boolean check(String mail, String pass){
		Scanner sc;
		try {
			sc = new Scanner(new File("database"));
		}catch (Exception e){
			e.printStackTrace();
			return true;
		}
		boolean find = false;
		while(!find && sc.hasNextLine()){
			String[] row = sc.nextLine().split(" ");
			if(row[1].equals(mail)){
				row = sc.nextLine().split(" ");
				if(row[1].equals(pass))
					find = true;
			}
		}
		return find;
	}

}
