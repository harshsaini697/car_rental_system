<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Home</title>
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
<h1>Admin Home</h1><br>
<a href="edituser.jsp">Edit User</a><br><br>
<a href="revokeuser.jsp">Revoke User</a><br><br>
<a href="enableuser.jsp">Enable User</a><br><br>
<a href="/mavride/UserController?action=listProfile"  target="_top"><span>Update Profile</span></a><br><br></a>
</body>
</html>