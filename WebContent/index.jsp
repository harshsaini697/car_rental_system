<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MavRide</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="stylesheet.css">
</head>
<body class="center">
<center>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><h1>Welcome to MavRide Web Application</h1></div>
  		<div class="content">  
  			<h2>LOGIN TO MAVRIDE</h2>
  			<br><br>
			<form name="loginForm" action="/mavride/LoginController" method="post">
    			<table class="logintable">
    				<tr>
    					<td> Username: </td>
    					<td><input name="username" id="username" type="text" value="<c:out value='${user.username}'/>"></td>
  						<td> <input name="userNameError"  value="<c:out value='${errorMsgs.userNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  					</tr>
  					<tr>
  						<td> Password: </td>
  						<td><input name="password" id="password" type="password" value="<c:out value='${user.password}'/>"></td>
  						<td> <input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  					</tr>
  					<tr>
  						<td></td>
  						<td id="forgotpassword"><a href="/forgotpassword.jsp"><small>Forgot Password?</small></td>
  					</tr>
  					<tr>
  						<td></td>
  						<td>
  							<input name="action" value="loginUser" type="hidden">
  							<input type="submit" value="Login">
  						</td>
  					</tr>
  					<tr><td>  </td><td></td>  </tr>
  					<tr><td>  </td><td></td>  </tr>
  					<tr>
  						<td>New User?</td>
  						<td><button type="button" onclick="window.location.href='http://localhost:8080/mavride/register.jsp'">Register Now</button></td>
  					</tr>
  				</table>
  				</form>
    	</div>
  	</div>
  </div>
</div>  
</body>
</html>