<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="bookName" scope="request" class="java.lang.String" />
<html>
	<head>
		<title>Login</title>
	</head>
	<style>
   		table {
    	width: 100%; /* Ширина таблицы */
   		}
   		td {
    	padding: 5px; /* Поля в ячейках */
    	vertical-align: top; /* Выравнивание по верхнему краю ячеек */
   		}
   
  	</style>
	<body>
	<h2><%= session.getAttribute("login") %></h2>
	<table >
		<tr>
			<td>
				
				<h3>Please enter book's name in field and press "Search"</h3>
				<form method=POST" action="/library/BookSearch" >
					<b> Book Name </b> 
					<input type="text" name="bookName" size="20"> <br><br>
					<input type="submit" value="Search">
				</form>
			<%@ taglib uri="/WEB-INF/libraryTag.tld" prefix="libraryTag" %>
		 	<libraryTag:getBooks bookName="<%= bookName%>"/>
		 	</td>	
		 	<td vertical-align=top>
				<h3>Please enter book's Id in field and press "Make Order"</h3> 	
		 		<form method=POST" action="/library/BookSearch" >
					<b> Book Id </b> 
					<input type="text" name="bookId" size="20"> <br><br>
					<input type="submit" value="Make Order">
				</form>
		 	</td> 
		</tr>
	</table> 	
	</body>
</html>