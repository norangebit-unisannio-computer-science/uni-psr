/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 21/11/17
 *
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Ex1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = 0;
        HttpSession session = request.getSession();
        if(!session.isNew())
            count = (Integer) session.getAttribute("count");
        session.setAttribute("count", ++count);
        response.getWriter().print("hai contattato il server "+count+" volte");
    }
}
