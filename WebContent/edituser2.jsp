<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="stylesheet.css">
<title>MavRide Update Profile</title>
</head>
<body>
<center>
<h2>UPDATE PROFILE</h2>
<p>${message}</p>
<c:remove var="message" scope="session" /> 
<form name="updateForm" action="/mavride/UserController?updateUser" method="post">
	<table class="updatetable">
	<c:forEach items="${profile}" var="item">
		<tr>
			<td> Prefix: </td>
			<td>
				<select name="prefix" id="prefix">
					<option value="${item.prefix}" ${item.prefix == item.prefix ? 'selected="selected"' : ''}>${item.prefix}</option>
					<option value="Mr.">Mr.</option>
					<option value="Mrs.">Mrs.</option>
					<option value="Master">Master</option>
					<option value="Miss">Miss</option>
				</select>
			</td>
		</tr>
		<tr>
  			<td> First Name: </td>
  			<td><input name="firstname" id="firstname" type="text" value="<c:out value='${item.firstname}'/>"></td>
  		</tr>
  		<tr>
  			<td> Last Name: </td>
  			<td><input name="lastname" id="lastname" type="text" value="<c:out value='${item.lastname}'/>"></td>
  		</tr>
  		<tr>
  			<td> UTA ID: </td>
  			<td> <input name="utaid" id="utaid" type="number" value="<c:out value='${item.utaid}'/>" disabled></td>
    	</tr>
  		<tr>
  			<td> Username: </td>
  			<td><input name="username" id="username" type="text" value="<c:out value='${item.username}'/>" disabled></td>
  		</tr>
  		<tr>
  			<td> Email: </td>
  			<td><input name="email" id="email" type="text" value="<c:out value='${item.email}'/>" disabled></td>
  		</tr>
  		<tr>
  			<td> Password: </td>
  			<td><input name="password" id="password" type="password" value="<c:out value='${item.password}'/>"></td>
  		</tr>
  		<tr>
  			<td> Confirm Password: </td>
  			<td><input name="cnfpassword" id="cnfpassword" type="password"  value="<c:out value='${item.cnfpassword}'/>"></td>
  		</tr>
  		<tr>
  			<td> Phone: </td>
  			<td><input name="phone" id="phone" type="number" placeholder="1234567890"  value="<c:out value='${item.phone}'/>"></td>
  		</tr>
  		<tr>
  			<td> Date of Birth: </td>
  			<td><input name="dob" id="dob" type="text" placeholder="YYYY-MM-DD" value="<c:out value='${item.dob}'/>"></td>
  	</tr>
  		<tr>
  			<td> Age: </td>
  			<td><input name="age" id="age" type="number" value="<c:out value='${item.age}'/>"></td>
  		</tr>
  		<tr>
  			<td> Country of Residence: </td>
  			<td><input name="country" id="country" type="text" value="<c:out value='${item.country}'/>"></td>
  		</tr>
  		<tr>
  			<td> Street Address: </td>
  			<td><input name="address" id="address" type="text" value="<c:out value='${item.address}'/>"></td>
  		</tr>
  		<tr>
  			<td> City: </td>
  			<td><input name="city" id="city" type="text" value="<c:out value='${item.city}'/>"></td>
  		</tr>
  		<tr>
  			<td> State: </td>
  			<td><input name="state" id="state" type="text" value="<c:out value='${item.state}'/>"></td>
  		</tr>
  		<tr>
  			<td> PIN Code: </td>
  			<td><input name="pin" id="pin" type="number" value="<c:out value='${item.pin}'/>"></td>
  		</tr>
  		<tr>
  			<td> Driving License Number: </td>
  			<td><input name="dlnumber" id="dlnumber" type="text" value="<c:out value='${item.dlnumber}'/>" disabled></td>
  		</tr>
  		<tr>
  			<td> Driving License Expiry Date: </td>
  			<td><input name="dlexp" id="dlexp" type="text" placeholder="YYYY-MM-DD"  value="<c:out value='${item.dlexp}'/>"></td>
  		</tr>
  		<tr>
  			<td> Issuing Country: </td>
  			<td><input name="dlcountry" id="dlcountry" type="text" value="<c:out value='${item.dlcountry}'/>"></td>
  		</tr>
  		<tr>
  			<td> Do you wish to become an 'Arlington Auto Club Member': </td>
  			<td><input name="aacm" id="aacm" type="text" value="<c:out value='${item.aacm}'/>"></td>
  		</tr>
  		<tr>
  			<td> User Type: </td>
  			<td><input name="usertype" id="usertype" type="text" value="<c:out value='${item.usertype}'/>"></td>
  		</tr>
  		
  		<tr>
  			<td></td>
  			<td><input name="status" id="status" type="hidden" value="Enabled"></td>
  		</tr>
  		<tr>
    		<td colspan="2"></td>
		</tr>
		<tr>
    		<td colspan="2"></td>
		</tr>
  		<tr>
  			<td></td>
  		</tr>
  		
  	</table>
  	<input name="action" value="updateUser" type="hidden">
  	</form>
  	<a href="/mavride/ReserveRentalController?action=returnHome"  target="_top"><span>Home</span></a>
  	</c:forEach>
</body>
</html>