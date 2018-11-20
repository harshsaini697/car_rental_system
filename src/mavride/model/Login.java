package mavride.model;

import java.io.Serializable;
import mavride.data.UsersDAO;
import mavride.model.LoginErrorMsgs;

public class Login implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private String username;
	private String password;
	
	public void setLogin (String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void validateLogin(String action, Login login, LoginErrorMsgs errorMsgs) {
		if(action.equals("loginUser")) {
			errorMsgs.setUserNameError(validateUser_name(action,login.getUsername()));
			errorMsgs.setPasswordError(validatePassword(action, login.getPassword()));
			errorMsgs.setErrorMsg(action);
		}
		else {
			
		}
	}
	
	public String validateUser_name(String action, String username) {
		String result = "";
		username = username.trim();
		if(username.equals(""))
			result = "Username cannot be blank";
		else if(UsersDAO.UserNameunique(username))
			result = "Username does not exist";
		return result;
	}
	
	public String validatePassword(String action, String password) {
		String result = "";
		password = password.trim();
		if(password.equals(""))
			result = "Password cannot be blank";
		else if(UsersDAO.Password(password))
			result = "Invalid password";
		return result;
	}
}
