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

public class UpdateUser implements Serializable{
	private static final long serialVersionUID = 3L;
	private String username;
	private String prefix;
	private String firstname;
	private String lastname;
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
	private String dlexp;
	private String dlcountry;
	private String aacm;
	private String usertype;
	
	public void setUpdateUser (String prefix,String firstname,String lastname,String password,String cnfpassword,String phone,String dob,String age,String country,String address,String city,String state,String pin,String dlexp,String dlcountry,String aacm,String usertype) {
		setPrefix(prefix);
		setFirstname(firstname);
		setLastname(lastname);
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
		setDlexp(dlexp);
		setDlcountry(dlcountry);
		setAacm(aacm);
		setUsertype(usertype);
		
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCnfpassword() {
		return cnfpassword;
	}

	public void setCnfpassword(String cnfpassword) {
		this.cnfpassword = cnfpassword;
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
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
	
	public void validateUpdateProfile (String action, UpdateUser updateuser, UpdateUserErrorMsgs errorMsgs){
		if(action.equals("updateUser")) {
			
			errorMsgs.setFirstNameError(validateFirst_name(action,updateuser.getFirstname()));
			errorMsgs.setLastNameError(validateLast_name(action,updateuser.getLastname()));
			errorMsgs.setPasswordError(validatePassword(action, updateuser.getPassword()));
			errorMsgs.setConfirmPasswordError(validateConfirmPassword(action, updateuser.getCnfpassword(), updateuser.getPassword()));
			errorMsgs.setPhoneError(validatePhone(action, updateuser.getPhone()));
			errorMsgs.setDobError(validateDob(action, updateuser.getDob()));
			errorMsgs.setAgeError(validateAge(action, updateuser.getAge()));
			errorMsgs.setCountryError(validateCountry(action, updateuser.getCountry()));
			errorMsgs.setAddressError(validateAddress(action, updateuser.getAddress()));
			errorMsgs.setCityError(validateCity(action,updateuser.getCity()));
			errorMsgs.setStateError(validateState(action, updateuser.getState()));
			errorMsgs.setPinError(validatePin(action, updateuser.getPin()));
			errorMsgs.setDlExpError(validateDlExp(action, updateuser.getDlexp()));
			errorMsgs.setDlCountryError(validateDlCountry(action, updateuser.getDlcountry()));
			errorMsgs.setErrorMsg(action);
		}
		
		else {
			
		}
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
			else
				result = "";
		}
		catch(Exception e) {
			result = "Invalid date format";
		}
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
			result = "Address must be between 4 and 45 characters";
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
			else
				result = "";
		}
		catch(Exception e) {
			result = "Invalid date format";
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
