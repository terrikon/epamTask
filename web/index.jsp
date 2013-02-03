<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="note" scope="request" class="java.lang.String" />
<html>
	<head><title>Login</title></head>
	<body>
		<h2>Please enter your login and password </h2>
		<form method=POST" action="/library/authentification">
			<b> <span style="padding:0px 15px;">Login</span> </b> <input type="text" name="login" size="20"> <br><br>
			<b> Password </b> <input type="text" name="pass" size="20">
			<input type="submit" value="Submit">
		</form>		
		<hr>
		<table>
			<tr>
	 
				<td><font color="red"> <%= note %> </font></td>
		
			</tr>

		</table>
	</body>
</html>