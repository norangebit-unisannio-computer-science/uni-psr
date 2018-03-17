<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<style><%@include file="/css/style.css"%></style>
		<title>Profilo Utente</title>
	</head>
	<body>
		<ul>
			<li class="menu_item"><a href="RegisterExam.html">Registra un esame</a></li>
			<li class="menu_item"><a href="/student/Exams">Lista esami</a></li>
		</ul>
		<br>
		<div class="news_box">
			<c:forEach items="${appelli}" var="item" varStatus="status">
				<h4 class="sub_title">${item.getExamName()}</h4>
				<p>Giorno: ${item.getExamDate()}</p>
				<p>Sede: ${item.getSede()}</p>
			</c:forEach>
		</div>
	</body>
</html>