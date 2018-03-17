<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style><%@include file="/css/style.css"%></style>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Lista esami</title>
	</head>
	<body>
		<a href="/student/Menu">Home</a>
		<h2 class="title">Gli esami che hai superato sono:</h2>

		<table border=1>
			<c:forEach items="${exams}" var="item" varStatus="status">
				<tr><td>${status.count}</td><td>${item}</td><td>${item.getExamVote()}</td><td><a href="/student/EditExam?edit=${status.count}">edit</a></td><td><a href="/student/RmExam?rm=${status.count}">delete</a> </td></tr>
			</c:forEach>
		</table>
	<p>La media vale: ${media}/30 </p>
	</body>
</html>