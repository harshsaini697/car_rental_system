<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rental Summary</title>
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
<h2>RENTAL SUMMARY</h2><br>
<form name="summaryForm" action="/mavride/ReserveRentalController?makePayment" method="get">
	<table>
		<tr>
			<td> Name: </td>
			<td> <c:out value="${firstname}" /> </td>
		</tr>
		<tr>
			<td> Car Name: </td>
			<td> <c:out value="${selectedCar}" /> </td>
		</tr>
		<tr>
			<td> Base Price: </td>
			<td> <c:out value="${baseprice}" /> </td>
		</tr>
		<tr>
			<td> Extras Price: </td>
			<td> <c:out value="${extrasprice}" /> </td>
		</tr>
		<tr>
			<td> Deposit Amount: </td>
			<td> <c:out value="${depositamt}" /> </td>
		</tr>
		<tr>
			<td> Tax Applied: </td>
			<td> <c:out value="${tax}" /> </td>
		</tr>
		<tr>
			<td> Discount: </td>
			<td> <c:out value="${discount}" /> </td>
		</tr>
		<tr>
			<td> Start Date: </td>
			<td> <c:out value="${startdate}" /> </td>
		</tr>
		<tr>
			<td> Start Time: </td>
			<td> <c:out value="${starttime}" /> </td>
		</tr>
		<tr>
			<td> End Date: </td>
			<td> <c:out value="${enddate}" /> </td>
		</tr>
		<tr>
			<td> End Time: </td>
			<td> <c:out value="${endtime}" /> </td>
		</tr>
		<tr>
			<td> Total Amount: </td>
			<td> <c:out value="${totalprice}" /> </td>
		</tr>
		
	</table>
	<input name="action" value="makePayment" type="hidden"><br>
  	<input type="submit" value="Confirm and Pay">
  	<input type="button" value="Cancel">
</form>
</body>
</html>