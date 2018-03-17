<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="model.Exam" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<style><%@include file="/css/style.css"%></style>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Modifica esame</title>
	</head>
	<body>
    	<h3 class="title">Modifica le informazioni dell'esame superato </h3>
		<form action="/student/RegisterExam" method="POST">
			<div class="centerH">
			<table>
				<% String date = new SimpleDateFormat("yyyy-MM-dd").format(((Exam)request.getAttribute("exam")).getExamDate()); %>
				<tr><td> Nome del corso: </td><td> <INPUT type="text" name="courseName" value="${exam.getCourseName()}"></td></tr>
				<tr><td> Nome del docente: </td><td> <INPUT type="text" name="teacherName" value="${exam.getTeacherName()}"></td></tr>
				<tr><td> Data dell'esame: </td><td> <INPUT type="date" name="examDate" value="<%= date%>"></td></tr>
				<tr><td> Voto: </td><td> <INPUT type="number" min="18" max="30" step="1" name="examVote" value="${exam.getExamVote()}"></td></tr>
			</table>
			</div>
			<div class="centerH">
				<INPUT class="button" type="submit" value="Modifica"> <INPUT class="button" type="reset" value="Cancella">
			</div>
		</form>
	</body>
</html>