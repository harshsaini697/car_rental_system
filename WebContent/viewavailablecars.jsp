<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Available Cars</title>
<link rel="stylesheet" href="stylesheet.css">
<c:remove var="errorMsgs" scope="session" /> 
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
<h2>View Available Cars</h2><br>
<form name="availableCarsForm" action="/mavride/ReserveRentalController?viewavailablecars" method="get">
	
	<table class="reservetable">
		<tr>
  			<td> Start Date: </td>
  			<td><input name="startdate" id="startdate" type="date" value="<c:out value='${rental.startdate}'/>"></td>
  			<td> <input name="startDateError"  value="<c:out value='${rentalErrorMsgs.startDateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  		<tr>
  			<td> End Date: </td>
  			<td> <input name="enddate" id="enddate" type="date" value="<c:out value='${rental.enddate}'/>"></td>
    	  	<td> <input name="endDateError"  value="<c:out value='${rentalErrorMsgs.endDateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    	</tr>
  		<tr>
  			<td> Number of Occupants: </td>
  			<td> <input name="occupants" id="occupants" type="text" value="<c:out value='${rental.occupants}'/>"></td>
  			<td> <input name="occupantsError"  value="<c:out value='${rentalErrorMsgs.occupantsError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  	</table>
  	<input name="action" value="viewavailablecars" type="hidden"><br>
  	<input type="submit" value="Continue">
  	</form>
  	<c:remove var="errorMsgs" scope="session" /> 
</body>
</html>