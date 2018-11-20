package mavride.model;

public class LoginErrorMsgs {
	
	private String errorMsg;
	private String userNameError;
	private String passwordError;
	
	public LoginErrorMsgs() {
		this.errorMsg = "";
		this.userNameError = "";
		this.passwordError = "";
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String action) {
		
			if(!userNameError.equals("") || !passwordError.equals(""))
				this.errorMsg = "Please correct the following errors";
		
	}
	
	public void setUserNameError(String userNameError) {
		this.userNameError = userNameError;
	}
	
	public String getUserNameError() {
		return userNameError;
	}
	
	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}
	
	public String getPasswordError() {
		return passwordError;
	}

}
