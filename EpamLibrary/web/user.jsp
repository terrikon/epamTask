<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/libraryTag.tld" prefix="libraryTag" %>
<jsp:useBean id="bookName" scope="request" class="java.lang.String" />
<jsp:useBean id="orderBookId" scope="request" class="java.lang.String" />
<html>
	<head>
		<title>Login</title>
	</head>
	<style>
   		table {
    	width: 100%; 
   		}
   		td {
    	padding: 5px; 
    	vertical-align: top; 
   		}
   
  	</style>
	<body>
	<h2>Welcome, <%= session.getAttribute("login") %>! </h2>
	<table >
		<tr>
			<td>
				
				<h3>Please enter book's name in field and press "Search"</h3>
				<form method=POST" action="/library/BookSearch" >
					<b> Book Name </b> 
					<input type="text" name="bookName" size="20"> <br><br>
					<input type="submit" value="Search">
				</form>
			
		 	<libraryTag:getBooks bookName="<%= bookName%>"/>
		 	</td>	
		 	<td>
				<h3>Please enter book's Id in field and press "Make Order"</h3> 	
		 		<form method=POST" action="/library/MakeOrder" >
					<b> Book Id </b> 
					<input type="text" name="bookId" size="20"> <br><br>
					<input type="submit" value="Make Order">
				</form>
				
			<libraryTag:makeOrder bookId="<%=orderBookId%>" 
				userId="<%= session.getAttribute("login").toString() %>"/>
		 	</td> 
		</tr>
	</table> 	
	</body>
</html>