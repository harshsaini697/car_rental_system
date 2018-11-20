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
<h2>Payment Confirmation</h2><br>
<form name="paymentForm" action="/mavride/ReserveRentalController?confirmRental" method="get">
<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
	<table>
		<tr>
  			<td> Name on Card: </td>
  			<td><input name="nameoncard" id="nameoncard" type="text" value="<c:out value='${rental.nameoncard}'/>"></td>
  		</tr>
  		<tr>
  			<td> Billing Address: </td>
  			<td><input name="billingaddress" id="billingaddress" type="text" value="<c:out value='${rental.billingaddress}'/>"></td>
  		</tr>
  		<tr>
  			<td> CC No: </td>
  			<td> <input name="ccno" id="ccno" type="number" value="<c:out value='${rental.ccno}'/>"></td>
  		</tr>
    	<tr>
			<td> Credit Card Type: </td>
			<td>
				<select name="cctype" id="cctype">
					<option value="American Express">American Express</option>
					<option value="VISA">VISA</option>
					<option value="Master Card">Master Card</option>
					<option value="Other">Other</option>
				</select>
			</td>
		</tr>
		<tr>
			<td> Expiry Month: </td>
			<td>
				<select name="ccmonth" id="ccmonth" placeholder="Select Month">
					<option value="January">January</option>
					<option value="February">February</option>
					<option value="March">March</option>
					<option value="April">April</option>
					<option value="May">May</option>
					<option value="June">June</option>
					<option value="July">July</option>
					<option value="August">August</option>
					<option value="September">September</option>
					<option value="October">October</option>
					<option value="November">November</option>
					<option value="December">December</option>
				</select>
			</td>
			
		</tr>
		<tr>
		<td> Expiry Year: </td>
			<td>
				<select name="ccyear" id="ccyear" placeholder="Select Year">
					<option value="2019">2019</option>
					<option value="2020">2020</option>
					<option value="2021">2021</option>
					<option value="2022">2022</option>
					<option value="2023">2023</option>
					<option value="2024">2024</option>
					<option value="2025">2025</option>
					<option value="2026">2026</option>
					<option value="2027">2027</option>
					<option value="2028">2028</option>
					<option value="2029">2029</option>
				</select>
			</td>
		</tr>
		<tr>
  			<td> CVV Number: </td>
  			<td> <input name="cvv" id="cvv" type="number" value="<c:out value='${rental.cvv}'/>"></td>
  		</tr>
  		<tr>
  			<td></td>
  			<td><input name="validationErrors"  value="<c:out value='${rentalErrorMsgs.confirmRentalError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 400px"  disabled="disabled" maxlength="60"></td>
  		</tr>
	</table>
	<input name="action" value="confirmRental" type="hidden"><br>
  	<input type="submit" value="Book Now">
  	<input type="button" value="Cancel">
</form>
</body>
</html>