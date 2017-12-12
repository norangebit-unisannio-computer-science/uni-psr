package filter;

/*
 *  Author: Raffaele Mignone
 *  Mat: 863/747
 *  Date: 12/12/17
 *
 */

import model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Filter2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        User u;
        if (session!=null && (u = (User)session.getAttribute("user"))!=null && u.getPassword()==request.getParameter("password").hashCode()) {
            chain.doFilter(request, response);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/NotAuthenticated.jsp");
        rd.forward(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
