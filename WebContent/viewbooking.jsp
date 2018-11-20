<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Bookings on Date</title>
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
<form name="viewBookingForm" action="/mavride/ReserveRentalController?viewBookingsonDate" method="get">
	
	<table class="reservetable">
		<tr>
  			<td> Select Date: </td>
  			<td><input name="sdate" id="sdate" type="date"></td>
  		</tr>
  	</table>
  	<input name="action" value="viewBookingsonDate" type="hidden"><br>
  	<input type="submit" value="Continue">
  	</form>
  	<c:remove var="errorMsgs" scope="session" /> 

</body>
</html>