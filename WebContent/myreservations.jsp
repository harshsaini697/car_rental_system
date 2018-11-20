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

<h2>All Reservations</h2><br>
<p>${message}</p>
<c:remove var="message" scope="session" /> 
<form name="myrentalForm" action="/mavride/ReserveRentalController" method="get">

     <div class="mainbar"><div class="submb"></div></div>
       <table class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 105px; ">Rental ID</th>
				<th class="myTableHead" style="width: 74px; ">Name</th>
				<th class="myTableHead" style="width: 74px; ">Carname</th> 
				<th class="myTableHead" style="width: 130px; ">Start Date</th>
				<th class="myTableHead" style="width: 63px; ">Start Time</th> 
				<th class="myTableHead" style="width: 63px; ">End Date</th> 
				<th class="myTableHead" style="width: 63px; ">End Time</th> 
				<th class="myTableHead" style="width: 63px; ">Total Amount</th>
			</tr>

 		<c:forEach items="${myrentals}" var="item">
			<tr class="myTableRow">
			<td style="width: 20px; "> <input type="radio" name="rentalid" id="rentalid" value="<c:out value="${item.rentalid}" />">${item.rentalid}</td>

			<td class="myTableCell" style="width: 150px; "><c:out value="${item.name}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.carname}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.startdate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.starttime}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.enddate}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.endtime}" /></td>
			<td class="myTableCell" style="width: 150px; "><c:out value="${item.totalamount}" /></td>
			
			</tr>
		</c:forEach>
 </table>
 <input type="radio" name="myaction" id="myaction" value="viewrental" checked="checked">View Rental
 <input type="radio" name="myaction" id="myaction1" value="cancelrental">Cancel Rental
 <input name="action" value="viewRental" type="hidden"><br>
  	<input type="submit" value="Continue" onclick="clicked(event)" >
  	
  	
  	</form>

</body>
<script>
function clicked(e)
{
	var myaction = document.getElementById("myaction1");
	if(myaction.checked == true){
		if(!confirm('Are you sure you want to delete this rental?'))e.preventDefault();
	}
    
}
</script>
</html>