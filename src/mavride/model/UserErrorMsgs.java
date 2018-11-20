package mavride.model;

public class UserErrorMsgs {
	
	private String errorMsg;
	private String IDUtaerror;
	private String firstNameError;
	private String lastNameError;
	private String userNameError;
	private String eMailError;
	private String confirmEmailError;
	private String passwordError;
	private String confirmPasswordError;
	private String phoneError;
	private String dobError;
	private String ageError;
	private String countryError;
	private String addressError;
	private String cityError;
	private String stateError;
	private String pinError;
	private String dlNumberError;
	private String dlExpError;
	private String dlCountryError;
	private String userRoleError;
	
	public UserErrorMsgs() {
		this.errorMsg = "";
		this.IDUtaerror = "";
		this.firstNameError = "";
		this.lastNameError = "";
		this.userNameError = "";
		this.eMailError = "";
		this.confirmEmailError = "";
		this.passwordError = "";
		this.confirmPasswordError = "";
		this.phoneError = "";
		this.dobError = "";
		this.ageError = "";
		this.countryError = "";
		this.addressError = "";
		this.cityError = "";
		this.stateError = "";
		this.pinError = "";
		this.dlNumberError = "";
		this.dlExpError = "";
		this.dlCountryError = "";
		this.userRoleError = "";
	}
	
	
	
	
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String action) {
		
			if(!firstNameError.equals("") || !lastNameError.equals("") || !userNameError.equals("") || !confirmEmailError.equals("") || !passwordError.equals("") || !confirmPasswordError.equals("") || !phoneError.equals("") || !dobError.equals("") || !ageError.equals("") || !countryError.equals("") || !addressError.equals("") || !cityError.equals("") || !stateError.equals("") || !pinError.equals("") || !dlNumberError.equals("") || !dlExpError.equals("") || !dlCountryError.equals(""))
				this.errorMsg = "Please correct the following errors";
			else {}
		
			if(!userNameError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}else {}
		
			if(!userNameError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}else {}
	
			if(!userNameError.equals("")) {
				this.errorMsg = "Please correct the following errors";
			}else {}
	}
	
	public String getIDUtaerror() {
		return IDUtaerror;
	}
	
	public void setIDUtaerror(String IDUtaerror) {
		this.IDUtaerror = IDUtaerror;
	}
	
	public String getFirstNameError() {
		return firstNameError;
	}
	
	public void setFirstNameError(String firstNameError) {
		this.firstNameError = firstNameError;
	}
	
	public void setLastNameError(String lastNameError) {
		this.lastNameError = lastNameError;
	}
	
	public String getLastNameError() {
		return lastNameError;
	}
	
	public void setUserNameError(String userNameError) {
		this.userNameError = userNameError;
	}
	
	public String getUserNameError() {
		return userNameError;
	}
	
	public void setEmailError(String eMailError) {
		this.eMailError = eMailError;
	}
	
	public String getEmailError() {
		return eMailError;
	}
	
	public void setConfirmEmailError(String confirmEmailError) {
		this.confirmEmailError = confirmEmailError;
	}
	
	public String getConfirmEmailError() {
		return confirmEmailError;
	}
	
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	
	public String getPasswordError() {
		return passwordError;
	}
	
	public void setConfirmPasswordError(String confirmPasswordError) {
		this.confirmPasswordError = confirmPasswordError;
	}
	
	public String getConfirmPasswordError() {
		return confirmPasswordError;
	}
	
	public void setPhoneError(String phoneError) {
		this.phoneError = phoneError;
	}
	
	public String getPhoneError() {
		return phoneError;
	}
	
	public void setDobError(String dobError) {
		this.dobError = dobError;
	}
	
	public String getDobError() {
		return dobError;
	}
	
	public void setAgeError(String ageError) {
		this.ageError = ageError;
	}
	
	public String getAgeError() {
		return ageError;
	}
	
	public void setCountryError(String countryError) {
		this.countryError = countryError;
	}
	
	public String getCountryError() {
		return countryError;
	}
	
	public void setAddressError(String addressError) {
		this.addressError = addressError;
	}
	
	public String getAddressError() {
		return addressError;
	}
	
	public void setCityError(String cityError) {
		this.cityError = cityError;
	}
	
	public String getCityError() {
		return cityError;
	}
	
	public void setStateError(String stateError) {
		this.stateError = stateError;
	}
	
	public String getStateError() {
		return stateError;
	}
	
	public void setPinError(String pinError) {
		this.pinError = pinError;
	}
	
	public String getPinError() {
		return pinError;
	}
	
	public void setDlNumberError(String dlNumberError) {
		this.dlNumberError = dlNumberError;
	}
	
	public String getDlNumberError() {
		return dlNumberError;
	}
	
	public void setDlExpError(String dlExpError) {
		this.dlExpError = dlExpError;
	}
	
	public String getDlExpError() {
		return dlExpError;
	}
	
	public void setDlCountryError(String dlCountryError) {
		this.dlCountryError = dlCountryError;
	}
	
	public String getDlCountryError() {
		return dlCountryError;
	}
	
	public void setUserRoleError(String userRoleError) {
		this.userRoleError = userRoleError;
	}
	
	public String getUserRoleError() {
		return userRoleError;
	}
	
}
