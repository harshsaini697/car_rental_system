<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enable User</title>
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
<p>${message}</p>
<c:remove var="message" scope="session" /> 
<form name="revokeUserForm" action="/mavride/UserController?enableUser" method="get">
	<input name="errMsg"  value="<c:out value='${rentalErrorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
	
	<table class="reservetable">
		<tr>
  			<td> Username: </td>
  			<td><input name="username" id="username" type="text"></td>
  			<td> <input name="userNameError"  value="<c:out value='${errorMsgs.userNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  	</table>
  	<input name="action" value="enableUser" type="hidden"><br>
  	<input type="submit" value="Enable User">
  	</form>
  	<c:remove var="errorMsgs" scope="session" /> 

</body>
</html>