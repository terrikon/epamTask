<%-- use the 'taglib' directive to make the JSTL 1.0 core tags available --%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%-- use the 'jsp:useBean' standard action to make the Date object available in page scope --%>
<jsp:useBean id="date" class="java.util.Date" />
<html>
<head><title>Login</title></head>
<body>
<h2>Please enter your login and password </h2>
<form method=POST" action="/library/authentification">
<b> Login </b> <input type="text" name="login" size="20"> <br><br>
<b> Password </b> <input type="text" name="pass" size="20">
<input type="submit" value="Submit">
</body>
</html>