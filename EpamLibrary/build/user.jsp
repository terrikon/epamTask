<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="login" scope="request" class="java.lang.String" />
<jsp:useBean id="books" scope="request" class="java.util.LinkedList" />
<jsp:useBean id="book" scope="request" class="dataBase.Book" />
<html>
	<head>
		<title>Login</title>
	</head>
	<body>
		<h2>Добро пожаловать,<%= login %></h2>
		<c:forEach var="book" items="${books}">
			<c:out value="${book.getName}"/>
		</c:forEach>
		
	</body>
</html>