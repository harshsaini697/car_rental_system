<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Extras</title>
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
<h2>ADD EXTRAS</h2><br>
<form name="addExtrasForm" action="/mavride/ReserveRentalController?rentalSummary" method="get">
  <input type="checkbox" name="gps" id="gps" value="<c:out value="${gps}"/>">GPS $<c:out value="${gps}"/>/day<br><br>
  <input type="checkbox" name="onstar" id="onstar" value="<c:out value="${onstar}"/>">OnStar $<c:out value="${onstar}"/>/day<br><br>
  <input type="checkbox" name="siriusxm" id="siriusxm" value="<c:out value="${siriusxm}"/>">SiriusXM $<c:out value="${siriusxm}"/>/day<br><br>
  <input name="action" value="rentalSummary" type="hidden"><br>
  	<input type="submit" value="Add Selected Extras">
  	</form>
</body>
</html>