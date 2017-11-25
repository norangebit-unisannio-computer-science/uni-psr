<%--
  Created by IntelliJ IDEA.
  User: orange
  Date: 22/11/17
  Time: 15.43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errore.jsp" %>
<html>
<head>
    <title>Calc</title>
</head>
<body>
    <h1><%= Integer.parseInt(request.getParameter("int1")) + Integer.parseInt(request.getParameter("int2"))%></h1>
</body>
</html>
