package ex3;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 21/11/17
 *
 */

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Authentication extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/plain");

        if (check(request.getParameter("email"), request.getParameter("password"))) {
            request.getSession(true).setAttribute("auth", "true");
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
            return false;
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
