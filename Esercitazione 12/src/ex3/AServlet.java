package ex3;/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 21/11/17
 *
 */

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Enumeration;

public class AServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        HttpSession session = request.getSession();
        Enumeration names = session.getAttributeNames();
        boolean authenticated = false;
        while (names.hasMoreElements() && !authenticated){
            String name = (String) names.nextElement();
            authenticated = name.equals("auth") && session.getAttribute(name).equals("true");
        }
        if(authenticated)
            pw.print("OK, sei gia' autenticato");
        else
            pw.print("Non sei autenticato! Non puoi accedere a questa risorsa");
    }
}
