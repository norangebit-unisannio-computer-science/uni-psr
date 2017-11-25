<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! int countM = 0; %>
<%! int countF = 0; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
  <HEAD>
     <TITLE>Welcome</TITLE>
  </HEAD>

  <BODY>
    <h1>
        <%  if(request.getParameter("sesso").equals("M")) { %>
                <%= request.getParameter("nome") %>, sei lutente n. <%= ++countM%>
        <%  }else{ %>
                <%= request.getParameter("nome") %>, sei lutente n. <%= ++countF%>
        <%  }
            Cookie cm = new Cookie("m", new Integer(countM).toString());
            Cookie cf = new Cookie("f", new Integer(countF).toString());
            response.addCookie(cm);
            response.addCookie(cf); %>
    </h1>
</BODY>
</HTML>