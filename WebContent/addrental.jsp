<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
<link rel="stylesheet" href="stylesheet.css">
<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("index.jsp");
%>
<ul>
  <li><h3>Hi, <%=userName %></h3></li>
  <li> <a href="/mavride/ReserveRentalController?action=returnHome"  target="_top"><span>Home</span></a> </li>
  <li><form action="LogoutController" method="post"><input type="submit" value="Logout" ></form></li>
</ul>
</head>
<center>
<body class="center">
<form name="addRentalForm" action="/mavride/ReserveRentalController?addRental" method="get">
	<table class="reservetable">
		<tr>
  			<td> Username: </td>
  			<td><input name="username" id="username" type="text"></td>
  		</tr>
  	</table>
  	<input name="validationErrors"  value="<c:out value='${rentalErrorMsgs.addRentalError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 170px"  disabled="disabled" maxlength="60">
  	<input name="action" value="addRental" type="hidden"><br>
  	<input type="submit" value="Continue">
  	</form>
  	<c:remove var="errorMsgs" scope="session" /> 
</body>
</html>