<%--
  Created by IntelliJ IDEA.
  User: orange
  Date: 25/11/17
  Time: 11.56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table</title>
</head>
<body>
    <% Cookie[] cookies = request.getCookies(); %>
    <table style="width:30%">
        <tr>
            <th>M</th>
            <th>F</th>
        </tr>
        <tr>
            <td><%= cookies[1].getValue() %></td>
            <td><%= cookies[2].getValue() %></td>
        </tr>
    </table>
</body>
</html>
