<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="stylesheet.css">
<title>MavRide Registration</title>
</head>
<body>
<center>
<h2>REGISTER ON MAVRIDE</h2>
<form name="registerForm" action="/mavride/UserController?saveUser" method="post">
	<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
	<table class="regtable">
		<tr>
			<td> Prefix: </td>
			<td>
				<select name="prefix" id="prefix">
					<option value=""></option>
					<option value="Mr.">Mr.</option>
					<option value="Mrs.">Mrs.</option>
					<option value="Master">Master</option>
					<option value="Miss">Miss</option>
				</select>
			</td>
		</tr>
		<tr>
  			<td> First Name: </td>
  			<td><input name="firstname" id="firstname" type="text" value="<c:out value='${user.firstname}'/>"></td>
  			<td> <input name="FirstNameError"  value="<c:out value='${errorMsgs.firstNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  		<tr>
  			<td> Last Name: </td>
  			<td><input name="lastname" id="lastname" type="text" value="<c:out value='${user.lastname}'/>"></td>
  			<td> <input name="lastNameError"  value="<c:out value='${errorMsgs.lastNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  		<tr>
  			<td> UTA ID: </td>
  			<td> <input name="utaid" id="utaid" type="number" value="<c:out value='${user.utaid}'/>"></td>
    		<td> <input name="IDUtaerror"  value="<c:out value='${errorMsgs.IDUtaerror}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
    	</tr>
  		<tr>
  			<td> Username: </td>
  			<td><input name="username" id="username" type="text" value="<c:out value='${user.username}'/>"></td>
  			<td> <input name="userNameError"  value="<c:out value='${errorMsgs.userNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  		<tr>
  			<td> Email: </td>
  			<td><input name="email" id="email" type="text" value="<c:out value='${user.email}'/>"></td>
  			<td> <input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  		<tr>
  			<td> Confirm Email: </td>
  			<td><input name="cnfemail" id="cnfemail" type="text"  value="<c:out value='${user.cnfemail}'/>"></td>
  			<td> <input name="confirmEmailError"  value="<c:out value='${errorMsgs.confirmEmailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		</tr>
  		<tr>
  			<td> Password: </td>
  			<td><input name="password" id="password" type="password" value="<c:out value='${user.password}'/>"></td>
  			<td> <input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Confirm Password: </td>
  			<td><input name="cnfpassword" id="cnfpassword" type="password"  value="<c:out value='${user.cnfpassword}'/>"></td>
  			<td> <input name="confirmPasswordError"  value="<c:out value='${errorMsgs.confirmPasswordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Phone: </td>
  			<td><input name="phone" id="phone" type="number" placeholder="1234567890"  value="<c:out value='${user.phone}'/>"></td>
  			<td> <input name="phoneError"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Date of Birth: </td>
  			<td><input name="dob" id="dob" type="text" placeholder="YYYY-MM-DD" value="<c:out value='${user.dob}'/>"></td>
  			<td> <input name="dobError"  value="<c:out value='${errorMsgs.dobError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Age: </td>
  			<td><input name="age" id="age" type="number" value="<c:out value='${user.age}'/>"></td>
  			<td> <input name="ageError"  value="<c:out value='${errorMsgs.ageError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Country of Residence: </td>
  			<td><input name="country" id="country" type="text" value="<c:out value='${user.country}'/>"></td>
  			<td> <input name="countryError"  value="<c:out value='${errorMsgs.countryError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Street Address: </td>
  			<td><input name="address" id="address" type="text" value="<c:out value='${user.address}'/>"></td>
  			<td> <input name="addressError"  value="<c:out value='${errorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> City: </td>
  			<td><input name="city" id="city" type="text" value="<c:out value='${user.city}'/>"></td>
  			<td> <input name="cityError"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> State: </td>
  			<td><input name="state" id="state" type="text" value="<c:out value='${user.state}'/>"></td>
  			<td> <input name="stateError"  value="<c:out value='${errorMsgs.stateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> PIN Code: </td>
  			<td><input name="pin" id="pin" type="number" value="<c:out value='${user.pin}'/>"></td>
  			<td> <input name="pinError"  value="<c:out value='${errorMsgs.pinError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Driving License Number: </td>
  			<td><input name="dlnumber" id="dlnumber" type="text" value="<c:out value='${user.dlnumber}'/>"></td>
  			<td> <input name="dlNumberError"  value="<c:out value='${errorMsgs.dlNumberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Driving License Expiry Date: </td>
  			<td><input name="dlexp" id="dlexp" type="text" placeholder="YYYY-MM-DD"  value="<c:out value='${user.dlexp}'/>"></td>
  			<td> <input name="dlExpError"  value="<c:out value='${errorMsgs.dlExpError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Issuing Country: </td>
  			<td><input name="dlcountry" id="dlcountry" type="text" value="<c:out value='${user.dlcountry}'/>"></td>
  			<td> <input name="dlCountryError"  value="<c:out value='${errorMsgs.dlCountryError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
  		</tr>
  		<tr>
  			<td> Do you wish to become an 'Arlington Auto Club Member': </td>
  			<td>
  				<input type="radio" name="aacm" id="aacm" value="yes" checked="checked">Yes
  				<input type="radio" name="aacm" id="aacm" value="no">No
  			</td>
  		</tr>
  		<tr>
  			<td> User Type: </td>
  			<td>
  				<input type="radio" name="usertype" id="usertype" value="faculty" checked="checked">Faculty
  				<input type="radio" name="usertype" id="usertype" value="student">Student
  			</td>
  		</tr>
  		<tr>
  			<td> User Role: </td>
  			<td>
				<select name="userrole" id="userrole" value="<c:out value='${user.userrole}'/>">
					<option value="User">User</option>
					<option value="Manager">Manager</option>
					<option value="Admin">Admin</option>
				</select>
			</td>
			<td> <input name="userRoleError"  value="<c:out value='${errorMsgs.userRoleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"> </td>
  		
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
  			<td id="registerbutton"></td>
  		</tr>
  	</table>
  	<input name="action" value="saveUser" type="hidden">
  	<input type="submit" value="Register">
  	</form>
</body>
</html>