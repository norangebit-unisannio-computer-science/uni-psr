<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! int count = 0; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
  <HEAD>
     <TITLE>Welcome</TITLE>
  </HEAD>

  <BODY>
    <h1><%= request.getParameter("nome") %>, sei lutente n. <%= ++count%> </h1>
</BODY>
</HTML> 
