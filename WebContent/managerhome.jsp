<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rental Manager Home</title>
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
  <li><form action="LogoutController" method="post"><input type="submit" value="Logout" ></form></li>
</ul>
</head>
<center>
<body class="center">
<h1>MavRide Web Application</h1>
<h1>Rental Manager Home</h1><br>
<a href="addrental.jsp">Add Rental</a><br><br>
<a href="/mavride/ReserveRentalController?action=listMyReservations"  target="_top"><span>Delete Rental</span></a><br><br>
<a href="calendar.jsp">View Calendar</a><br><br>
<a href="/mavride/ReserveRentalController?action=viewBookings">View Bookings</a><br><br>
<a href="/mavride/ReserveRentalController?action=viewCars">View Available Cars</a><br><br>
<a href="/mavride/ReserveRentalController?action=addCar">Add Car</a><br><br>
<a href="/mavride/ReserveRentalController?action=delCar">Delete Car</a><br><br>
<a href="/mavride/UserController?action=listProfile"  target="_top"><span>Update Profile</span></a><br><br></a>
</body>
</html>