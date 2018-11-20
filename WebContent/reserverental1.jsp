<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:remove var="errorMsgs" scope="session" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reserve Rental</title>
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
<h2>RESERVE RENTAL</h2><br>
<form name="reserveRentalForm" action="/mavride/ReserveRentalController?listCars" method="get">
	<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
	<table class="reservetable">
  		<tr>
  			<td> Start Time: </td>
  			<td>
  				<select name="starttime" id="starttime">
  					<c:forEach items="${timeList}" var="item">
					<option value="<c:out value="${item}" />"><c:out value="${item}" /></option>
					</c:forEach>
				</select>
  			</td>
  		</tr>
  		<tr>
  			<td> End Time: </td>
  			<td>
  				<select name="endtime" id="endtime">
  					<c:forEach items="${timeList1}" var="item1">
					<option value="<c:out value="${item1}" />"><c:out value="${item1}" /></option>
					</c:forEach>
				</select>
  			</td>
  			<td> <input name="timeError"  value="<c:out value='${rentalErrorMsgs.timeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  	</table>
  	<input name="action" value="listCars" type="hidden"><br>
  	<input type="submit" value="Search Cars">
  	</form>
</body>
</html>