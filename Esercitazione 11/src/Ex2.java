/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 17/11/17
 *
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Ex2 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print(req.getParameter("firstname")+", registrazione effettuata.");
    }
}
