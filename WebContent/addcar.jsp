<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Car</title>
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
<form name="addCarForm" action="/mavride/ReserveRentalController?addCar1" method="get">
	
	<table class="reservetable">
		<tr>
  			<td> Carname: </td>
  			<td><input name="carname" id="carname" type="text" value="<c:out value='${rental.carname}'/>"></td>
  		</tr>
  		<tr>
  			<td> Capacity: </td>
  			<td><input name="capacity" id="capacity" type="number" value="<c:out value='${rental.capacity}'/>"></td>
  		</tr>
  		<tr>
  			<td> Weekday Rate: </td>
  			<td><input name="weekdayrate" id="weekdayrate" type="text" value="<c:out value='${rental.weekdayrate}'/>"></td>
  		</tr>
  		<tr>
  			<td> Weekend Rate: </td>
  			<td><input name="weekendrate" id="weekendrate" type="text" value="<c:out value='${rental.weekendrate}'/>"></td>
  		</tr>
  		<tr>
  			<td> Week Rate: </td>
  			<td><input name="weekrate" id="weekrate" type="text" value="<c:out value='${rental.weekrate}'/>"></td>
  		</tr>
  		<tr>
  			<td> GPS Rate: </td>
  			<td><input name="gpsrate" id="gpsrate" type="text" value="<c:out value='${rental.gpsrate}'/>"></td>
  		</tr>
  		<tr>
  			<td> OnStar Rate: </td>
  			<td><input name="onstarrate" id="onstarrate" type="text" value="<c:out value='${rental.onstarrate}'/>"></td>
  		</tr>
  		<tr>
  			<td> SiriusXM Rate: </td>
  			<td><input name="siriusxmrate" id="siriusxmrate" type="text" value="<c:out value='${rental.siriusxmrate}'/>"></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td><input name="validationErrors"  value="<c:out value='${rentalErrorMsgs.addCarError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 400px"  disabled="disabled" maxlength="60"></td>
  		</tr>
  	</table>
  	<input name="action" value="addCar1" type="hidden"><br>
  	<input type="submit" value="Continue">
  	</form>
  	<c:remove var="errorMsgs" scope="session" /> 

</body>
</html>