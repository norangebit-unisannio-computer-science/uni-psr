package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RestrictedFilter implements Filter {

    public RestrictedFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession(false);
		
		if((session != null) && (session.getAttribute("user") != null))
			chain.doFilter(request, response);
		else
		{
			((HttpServletResponse)response).setHeader("Cache-control", "no-cache");
			request.getRequestDispatcher("/NotAuthenticated.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
