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
<center>
<h2>Rental Confirmation</h2>
<p>${message}</p>
<c:remove var="message" scope="session" /> 
	<table class="rentalconfirmationtable">
	
		<tr>
  			<td> Rental ID: </td>
  			<td><c:out value='${rentalsummary[0]}'/></td>
  		</tr>
		<tr>
  			<td> User Name: </td>
  			<td><c:out value='${rentalsummary[1]}'/></td>
  		</tr>
  		<tr>
  			<td> Name: </td>
  			<td><c:out value='${rentalsummary[2]}'/></td>
    	</tr>
  		<tr>
  			<td> Carname: </td>
  			<td><c:out value='${rentalsummary[3]}'/></td>
  		</tr>
  		<tr>
  			<td> Base Price: </td>
  			<td><c:out value='${rentalsummary[4]}'/></td>
  		</tr>
  		<tr>
  			<td> Tax Applied: </td>
  			<td><c:out value='${rentalsummary[5]}'/></td>
  		</tr>
  		<tr>
  			<td> Discount: </td>
  			<td><c:out value='${rentalsummary[6]}'/></td>
  		</tr>
  		<tr>
  			<td> Start Date: </td>
  			<td><c:out value='${rentalsummary[7]}'/></td>
  		</tr>
  		<tr>
  			<td> Start Time: </td>
  			<td><c:out value='${rentalsummary[8]}'/></td>
  		</tr>
  		<tr>
  			<td> End Date: </td>
  			<td><c:out value='${rentalsummary[9]}'/></td>
  		</tr>
  		<tr>
  			<td> End Time: </td>
  			<td><c:out value='${rentalsummary[10]}'/></td>
  		</tr>
  		<tr>
  			<td> Total Amount: </td>
  			<td><c:out value='${rentalsummary[11]}'/></td>
  		</tr>
  		<tr>
  			<td> Name On Card: </td>
  			<td><c:out value='${rentalsummary[12]}'/></td>
  		</tr>
  		<tr>
  			<td> Billing Address: </td>
  			<td><c:out value='${rentalsummary[13]}'/></td>
  		</tr>
  		<tr>
  			<td> CC No: </td>
  			<td><c:out value='${rentalsummary[14]}'/></td>
  		</tr>
  		<tr>
  			<td> CC Type: </td>
  			<td><c:out value='${rentalsummary[15]}'/></td>
  		</tr>
  		<tr>
  			<td> Exp Date: </td>
  			<td><c:out value='${rentalsummary[16]}'/></td>
  		</tr>
  		<tr>
  			<td> CVV No: </td>
  			<td><c:out value='${rentalsummary[17]}'/></td>
  		</tr>
  	</table>
  	<br>
  	<a href="/mavride/ReserveRentalController?action=listMyReservations"  target="_top"><span>All Reservations</span></a><br>
  	
</body>
</html>