package mavride.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mavride.model.Login;
import mavride.model.LoginErrorMsgs;

import org.junit.*;

@RunWith(JUnitParamsRunner.class)

public class LoginTest {
    Login login;
    LoginErrorMsgs lgnerrMsgs;
    
    @Before
    public void setUp() throws Exception{
    	login = new Login();
    	lgnerrMsgs = new LoginErrorMsgs();
    }
    
	@Test
	@FileParameters("csv/LoginTest.csv")
	public void test(String testcaseNo, String action, String  Username, String Password, String userNameError, String passwordError, String errorMsg) {
		login.setLogin(Username, Password);
		login.validateLogin(action, login, lgnerrMsgs);
		//lgnerrMsgs.setErrorMsg(action);
		assertTrue(userNameError.equals(lgnerrMsgs.getUserNameError()));
		assertTrue(passwordError.equals(lgnerrMsgs.getPasswordError()));
		assertTrue(errorMsg.equals(lgnerrMsgs.getErrorMsg()));
		
	}

}
