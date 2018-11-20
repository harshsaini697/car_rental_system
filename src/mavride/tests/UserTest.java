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
import mavride.model.Rental;
import mavride.model.RentalErrorMsgs;
import mavride.model.User;
import mavride.model.UserErrorMsgs;

import org.junit.*;

@RunWith(JUnitParamsRunner.class)

public class UserTest {
	User user;
	UserErrorMsgs usrerrMsgs;
	
	@Before
    public void setUp() throws Exception{
		user = new User();
		usrerrMsgs = new UserErrorMsgs();
    }
	
	@Test
	@FileParameters("csv/UserTest.csv")
	public void test(String testcaseNo, String action, String prefix,String firstname,String lastname,String utaid,String username,String email, String cnfemail,String password,String cnfpassword,String phone,String dob,String age,String country,String address,String city,String state,String pin,String dlnumber,String dlexp,String dlcountry,String aacm,String usertype,String userrole,String status, String firstNameError, String lastNameError, String IDUtaerror, String userNameError, String eMailError, String confirmEmailError, String passwordError, String confirmPasswordError, String phoneError, String dobError, String ageError, String countryError, String addressError, String cityError, String stateError, String pinError, String dlNumberError, String dlExpError, String dlCountryError, String userRoleError, String errorMsg ) {
		user.setUser(prefix,firstname,lastname,utaid,username,email,cnfemail,password,cnfpassword,phone,dob,age,country,address,city,state,pin,dlnumber,dlexp,dlcountry,aacm,usertype,userrole,status);
		user.validateUser(action, user, usrerrMsgs);
		assertTrue(IDUtaerror.equals(usrerrMsgs.getIDUtaerror()));
		assertTrue(firstNameError.equals(usrerrMsgs.getFirstNameError()));
		assertTrue(lastNameError.equals(usrerrMsgs.getLastNameError()));
		assertTrue(userNameError.equals(usrerrMsgs.getUserNameError()));
		assertTrue(eMailError.equals(usrerrMsgs.getEmailError()));
		assertTrue(confirmEmailError.equals(usrerrMsgs.getConfirmEmailError()));
		assertTrue(passwordError.equals(usrerrMsgs.getPasswordError()));
		assertTrue(confirmPasswordError.equals(usrerrMsgs.getConfirmPasswordError()));
		assertTrue(phoneError.equals(usrerrMsgs.getPhoneError()));
		assertTrue(dobError.equals(usrerrMsgs.getDobError()));
		assertTrue(ageError.equals(usrerrMsgs.getAgeError()));
		assertTrue(countryError.equals(usrerrMsgs.getCountryError()));
		assertTrue(addressError.equals(usrerrMsgs.getAddressError()));
		assertTrue(cityError.equals(usrerrMsgs.getCityError()));
		assertTrue(stateError.equals(usrerrMsgs.getStateError()));
		assertTrue(pinError.equals(usrerrMsgs.getPinError()));
		assertTrue(dlNumberError.equals(usrerrMsgs.getDlNumberError()));
		assertTrue(dlExpError.equals(usrerrMsgs.getDlExpError()));
		assertTrue(dlCountryError.equals(usrerrMsgs.getDlCountryError()));
		assertTrue(userRoleError.equals(usrerrMsgs.getUserRoleError()));
		assertTrue(errorMsg.equals(usrerrMsgs.getErrorMsg()));
		System.out.println("userrole error = "+usrerrMsgs.getUserRoleError());
	}
}
