<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="login" scope="request" class="java.lang.String" />
<html>
<head><title>Login</title></head>
<body>
<h2>Добро пожаловать,<%= login %></h2>
</body>
</html>