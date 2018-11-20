<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Choose Car</title>
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
<h2>CHOOSE CAR</h2><br>
<form name="chooseCarForm" action="/mavride/ReserveRentalController?selectExtras" method="get">

     <div class="mainbar"><div class="submb"></div></div>
       <table class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 105px; ">Car Name</th>
				<th class="myTableHead" style="width: 74px; ">Capacity</th> 
				<th class="myTableHead" style="width: 130px; ">Weekday Rate</th>
				<th class="myTableHead" style="width: 63px; ">Weekend Rate</th> 
				<th class="myTableHead" style="width: 63px; ">Week Rate</th> 
				<th class="myTableHead" style="width: 63px; ">Gps Rate</th> 
				<th class="myTableHead" style="width: 63px; ">OnStar Rate</th> 
				<th class="myTableHead" style="width: 63px; ">SiriusXM Rate</th> 
			</tr>

 		<c:forEach items="${car}" var="item">
			<tr class="myTableRow">
			<td style="width: 200px; "> <input type="radio" name="selectedCar" id="selectedCar" value="<c:out value="${item.carname}" />">${item.carname}</td>
<%-- 			<td class="myTableCell" style="width: 150px; "><c:out value="${item.carname}" /></td> --%>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.capacity}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.weekdayrate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.weekendrate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.weekrate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.gpsrate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.onstarrate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.siriusxmrate}" /></td>
			
			</tr>
		</c:forEach>
 </table>
 <input name="action" value="selectExtras" type="hidden"><br>
  	<input type="submit" value="Continue">
  	</form>
</body>
</html>