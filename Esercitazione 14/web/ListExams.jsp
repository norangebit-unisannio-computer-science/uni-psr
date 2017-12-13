<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Lista esami</title>
	</head>
	<body>
		<a href="/Menu.html">Home</a>
		<h2>Gli esami che hai superato sono:</h2>

		<table border=1>
			<c:forEach items="${exams}" var="item" varStatus="status">
				<tr><td>${status.count}</td><td>${item}</td></tr>
			</c:forEach>
		</table>
	</body>
</html>