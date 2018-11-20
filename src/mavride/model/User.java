package mavride.model;

import java.io.Serializable;
import mavride.data.UsersDAO;
import mavride.model.UserErrorMsgs;
import java.util.*;
import java.lang.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private String prefix;
	private String firstname;
	private String lastname;
	private String utaid;
	private String username;
	private String email;
	private String cnfemail;
	private String password;
	private String cnfpassword;
	private String phone;
	private String dob;
	private String age;
	private String country;
	private String address;
	private String city;
	private String state;
	private String pin;
	private String dlnumber;
	private String dlexp;
	private String dlcountry;
	private String aacm;
	private String usertype;
	private String userrole;
	private String status;
	
	public void setUser (String prefix,String firstname,String lastname,String utaid,String username,String email, String cnfemail,String password,String cnfpassword,String phone,String dob,String age,String country,String address,String city,String state,String pin,String dlnumber,String dlexp,String dlcountry,String aacm,String usertype,String userrole,String status) {
		setPrefix(prefix);
		setFirstname(firstname);
		setLastname(lastname);
		setUtaid(utaid);
		setUsername(username);
		setEmail(email);
		setCnfemail(cnfemail);
		setPassword(password);
		setCnfpassword(cnfpassword);
		setPhone(phone);
		setDob(dob);
		setAge(age);
		setCountry(country);
		setAddress(address);
		setCity(city);
		setState(state);
		setPin(pin);
		setDlnumber(dlnumber);
		setDlexp(dlexp);
		setDlcountry(dlcountry);
		setAacm(aacm);
		setUsertype(usertype);
		setUserrole(userrole);
		setStatus(status);
	}
	
	
	
	public String getCnfpassword() {
		return cnfpassword;
	}

	public void setCnfpassword(String cnfpassword) {
		this.cnfpassword = cnfpassword;
	}

	
	public String getCnfemail() {
		return cnfemail;
	}

	public void setCnfemail(String cnfemail) {
		this.cnfemail = cnfemail;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUtaid() {
		return utaid;
	}

	public void setUtaid(String utaid) {
		this.utaid = utaid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getDlnumber() {
		return dlnumber;
	}

	public void setDlnumber(String dlnumber) {
		this.dlnumber = dlnumber;
	}

	public String getDlexp() {
		return dlexp;
	}

	public void setDlexp(String dlexp) {
		this.dlexp = dlexp;
	}

	public String getDlcountry() {
		return dlcountry;
	}

	public void setDlcountry(String dlcountry) {
		this.dlcountry = dlcountry;
	}

	public String getAacm() {
		return aacm;
	}

	public void setAacm(String aacm) {
		this.aacm = aacm;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void validateUser (String action, User user, UserErrorMsgs errorMsgs){
		if(action.equals("saveUser")) {
			errorMsgs.setIDUtaerror(validateUtaid(action,user.getUtaid()));
			errorMsgs.setFirstNameError(validateFirst_name(action,user.getFirstname()));
			errorMsgs.setLastNameError(validateLast_name(action,user.getLastname()));
			errorMsgs.setUserNameError(validateUser_name(action,user.getUsername()));
			errorMsgs.setEmailError(validateEmail(action, user.getEmail()));
			errorMsgs.setConfirmEmailError(validateConfirmEmail(action, user.getCnfemail(), user.getEmail()));
			errorMsgs.setPasswordError(validatePassword(action, user.getPassword()));
			errorMsgs.setConfirmPasswordError(validateConfirmPassword(action, user.getCnfpassword(), user.getPassword()));
			errorMsgs.setPhoneError(validatePhone(action, user.getPhone()));
			errorMsgs.setDobError(validateDob(action, user.getDob()));
			errorMsgs.setAgeError(validateAge(action, user.getAge()));
			errorMsgs.setCountryError(validateCountry(action, user.getCountry()));
			errorMsgs.setAddressError(validateAddress(action, user.getAddress()));
			errorMsgs.setCityError(validateCity(action,user.getCity()));
			errorMsgs.setStateError(validateState(action, user.getState()));
			errorMsgs.setPinError(validatePin(action, user.getPin()));
			errorMsgs.setDlNumberError(validateDlNumber(action, user.getDlnumber()));
			errorMsgs.setDlExpError(validateDlExp(action, user.getDlexp()));
			errorMsgs.setDlCountryError(validateDlCountry(action, user.getDlcountry()));
			errorMsgs.setUserRoleError(validateUser_role(action,user.getUserrole(),user.getUtaid()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("edituser")) {
			errorMsgs.setUserNameError(validateUser_name1(action,user.getUsername()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("revokeuser")) {
			errorMsgs.setUserNameError(validateUser_name1(action,user.getUsername()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("enableuser")) {
			errorMsgs.setUserNameError(validateUser_name1(action,user.getUsername()));
			errorMsgs.setErrorMsg(action);
		}
	}
	
	public String validateUser_name1(String action, String username) {
		String result = "";
		username = username.trim();
		if(username.equals(""))
			result = "Username cannot be blank";
		else if(UsersDAO.UserNameunique(username))
			result = "Username does not exist";
		else
			result = "";
		return result;
	}
	
	public String validateUser_role(String action, String userrole, String utaid) {
		String result = "";
		if(!UsersDAO.UserRoleunique(userrole,utaid))
			result = "Userrole with this UTA ID already exists";
		return result;
	}
	
	private String validateDlCountry(String action, String dlcountry) {
		String result = "";
		dlcountry = dlcountry.trim();
		if(dlcountry.equals(""))
			result = "Country cannot be blank";
		else if(!stringSize(dlcountry,4,45))
			result = "Country must be between 4 and 45 characters";
		else if(!dlcountry.matches("^[a-zA-Z- ]+$"))
			result = "Country must contain only characters hyphens and spaces";
		return result;
	}
	
	private String validateDlExp(String action, String dlexp) {
		String result = "";
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			Date date1 = sdf.parse(dlexp);
			Date date2 = sdf.parse(dtf.format(localDate));
			if(!dlexp.matches("^\\d{4}-\\d{2}-\\d{2}$"))
				result = "Invalid date format";
			else if(date1.compareTo(date2) < 0)
				result = "Date is in the past";					
		}
		catch(Exception e) {
			result = "Invalid date format";
		}
		return result;
		
	}
	
	private String validateDlNumber(String action, String dlnumber) {
		String result = "";
		dlnumber = dlnumber.trim();
		if(dlnumber.equals(""))
			result = "Driving License Number cannot be blank";
		else if(!stringSize(dlnumber,8,20))
			result = "Driving License Number must be between 8 and 20 characters";
		return result;
	}
	
	private String validatePin(String action, String pin) {
		String result = "";
		pin = pin.trim();
		if(pin.equals(""))
			result = "PIN Code cannot be blank";
		else if(!stringSize(pin,3,10))
			result = "PIN Code must be between 3 and 10 characters";
		return result;
	}
	
	private String validateState(String action, String state) {
		String result = "";
		state = state.trim();
		if(state.equals(""))
			result = "State cannot be blank";
		else if(!stringSize(state,4,45))
			result = "State must be between 4 and 45 characters";
		else if(!state.matches("^[a-zA-Z- ]+$"))
			result = "State must contain only characters hyphens and spaces";
		return result;
	}
	
	private String validateCity(String action, String city) {
		String result = "";
		city = city.trim();
		if(city.equals(""))
			result = "City cannot be blank";
		else if(!stringSize(city,4,45))
			result = "City must be between 4 and 45 characters";
		else if(!city.matches("^[a-zA-Z- ]+$"))
			result = "City must contain only characters hyphens and spaces";
		return result;
	}
	
	private String validateAddress(String action, String address) {
		String result = "";
		address = address.trim();
		if(address.equals(""))
			result = "Address cannot be blank";
		else if(!stringSize(address,3,45))
			result = "Address must be between 3 and 45 characters";
		else if(!address.matches("^[0-9a-zA-Z- '/,]+$"))
			result = "Address must contain only characters hyphens spaces";
		return result;
	}
	
	private String validateCountry(String action, String country) {
		String result = "";
		country = country.trim();
		if(country.equals(""))
			result = "Country cannot be blank";
		else if(!stringSize(country,4,45))
			result = "Country must be between 4 and 45 characters";
		else if(!country.matches("^[a-zA-Z- ]+$"))
			result = "Country must contain only characters hyphens and spaces";
		return result;
	}
	
	private String validateAge(String action, String age) {
		String result = "";
		if(age.equals("")) {
			age="10";
			result = "Age cannot be blank";
		}
		else if(Integer.parseInt(age)<18)
			result = "Age must be 18 or older";
		else if(Integer.parseInt(age)>120)
			result = "Age must be 120 or younger";
		return result;
	}
	
	private String validateDob(String action, String dob){
		String result = "";
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			Date date1 = sdf.parse(dob);
			Date date2 = sdf.parse(dtf.format(localDate));
			if(!dob.matches("^\\d{4}-\\d{2}-\\d{2}$"))
				result = "Invalid date format";
			else if(date1.compareTo(date2) > 0)
				result = "Date is in future";					
		}
		catch(Exception e) {
			result = "Invalid date format";
		}
		return result;
		
	}
	
	private String validateUtaid(String action, String utaid) {
		String result = "";
		if(utaid.equals(""))
			result = "UTA ID cannot be blank";
		else if(utaid.length()!=10)
			result = "UTA ID must be 10 digits";
		return result;
	}
	
	private String validateFirst_name(String action, String firstname) {
		String result = "";
		firstname = firstname.trim();
		if(firstname.equals(""))
			result = "First Name cannot be blank";
		else if(!stringSize(firstname,3,45))
			result = "First Name must be between 3 and 45 characters";
		else if(!firstname.matches("^[a-zA-Z]+$"))
			result = "First Name must contain only characters";
		return result;
	}
	
	public String validateLast_name(String action, String lastname) {
		String result = "";
		lastname = lastname.trim();
		if(lastname.equals(""))
			result = "Last Name cannot be blank";
		else if(!stringSize(lastname,3,45))
			result = "Last Name must be between 3 and 45 characters";
		else if(!lastname.matches("^[a-zA-Z]+$"))
			result = "Last Name must contain only characters";
		return result;
	}
	
	public String validateUser_name(String action, String username) {
		String result = "";
		username = username.trim();
		if(username.equals(""))
			result = "Username cannot be blank";
		else if(!stringSize(username,3,45))
			result = "Username must be between 3 and 45 characters";
		else if(!username.matches("^[a-zA-Z0-9_.-]+$"))
			result = "Username must contain only characters numbers underscores periods and hyphens";
		else if(!UsersDAO.UserNameunique(username))
			result = "Username already exists";
		return result;
	}
	
	public String validateEmail(String action, String email) {
		String result = "";
		if(email.equals(""))
			result = "Email cannot be blank";
		else if(!stringSize(email,7,45))
			result = "Email must be between 7 and 45 characters";
		else if(!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+(.com|.gov|.net|.org|.edu|.mil)$"))
			result = "Invalid Email ID";
		return result;
	}
	
	public String validateConfirmEmail(String action, String cnfemail, String email) {
		String result = "";
		if(cnfemail.equals(""))
			result = "Email cannot be blank";
		else if(!cnfemail.equals(email))
			result = "Confirm email must be same as email";
		return result;
	}
	
	public String validatePassword(String action, String password) {
		String result = "";
		password = password.trim();
		if(password.equals(""))
			result = "Password cannot be blank";
		else if(password.length()<6)
			result = "Password must be atleast 6 characters long";
		else if(password.length()>45)
			result = "Password must be less than 45 characters long";
		else if(!password.matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$"))
			result = "Password must contain atleast one digit one lower case letter one upper case letter and one special character";
		return result;
	}
	
	public String validateConfirmPassword(String action, String cnfpassword, String password) {
		String result = "";
		cnfpassword = cnfpassword.trim();
		if(cnfpassword.equals(""))
			result = "Password cannot be blank";
		else if(!cnfpassword.equals(password))
			result = "Passwords do not match";
		return result;
	}
	
	public String validatePhone(String action, String phone) {
		String result = "";
		if(phone.equals(""))
			result = "Phone number cannot be blank";
		else if(phone.length()!=10) {
			result = "Phone number must exactly be 10 digits";
		}
		return result;
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
}
