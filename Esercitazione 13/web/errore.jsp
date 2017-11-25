<%--
  Created by IntelliJ IDEA.
  User: orange
  Date: 24/11/17
  Time: 10.30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <title>Errore</title>
</head>
<body>
    <H1>Errore</H1>
    <p><%= exception.getMessage() %></p>
</body>
</html>
