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

public class Ex3 extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        float f1 = Float.parseFloat(req.getParameter("float1"));
        float f2 = Float.parseFloat(req.getParameter("float2"));
        float result;
        switch(req.getParameter("op")){
            case "-":
               result = f1-f2;
               break;
            case "+":
                result = f1+f2;
                break;
            case "*":
                result = f1*f2;
                break;
            case "/":
                result = f1/f2;
                break;
            default:
                result = 0;
        }
        out.print(result);
    }
}
