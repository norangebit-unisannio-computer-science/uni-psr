<%@ page import="java.util.Enumeration" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
  <HEAD>
     <TITLE>Welcome</TITLE>
  </HEAD>

  <BODY>
    <%  int count;
        boolean hasCount = false;
        Enumeration names = session.getAttributeNames();
        while (names.hasMoreElements() && !hasCount)
            hasCount = names.nextElement().equals("count");

        if(!hasCount)
            count = 0;
        else
            count = (Integer) session.getAttribute("count"); %>

    <h1>Hai contattato questa pagina <%= ++count %> volte</h1>
    <% session.setAttribute("count", count); %>
</BODY>
</HTML> 
