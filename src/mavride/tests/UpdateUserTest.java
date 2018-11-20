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
import mavride.model.UpdateUser;
import mavride.model.UpdateUserErrorMsgs;
import mavride.model.User;
import mavride.model.UserErrorMsgs;

import org.junit.*;

@RunWith(JUnitParamsRunner.class)

public class UpdateUserTest {
	UpdateUser updateuser;
	UpdateUserErrorMsgs uusrerrMsgs;
	
	@Before
    public void setUp() throws Exception{
		updateuser = new UpdateUser();
		uusrerrMsgs = new UpdateUserErrorMsgs();
    }
	
	@Test
	@FileParameters("csv/UpdateUserTest.csv")
	public void test(String testcaseNo, String action, String prefix,String firstname,String lastname,String password,String cnfpassword,String phone,String dob,String age,String country,String address,String city,String state,String pin,String dlexp,String dlcountry,String aacm,String usertype, String firstNameError, String lastNameError, String passwordError, String confirmPasswordError, String phoneError, String dobError, String ageError, String countryError, String addressError, String cityError, String stateError, String pinError, String dlExpError, String dlCountryError, String errorMsg) {
		updateuser.setUpdateUser(prefix,firstname,lastname,password,cnfpassword,phone,dob,age,country,address,city,state,pin,dlexp,dlcountry,aacm,usertype);
		updateuser.validateUpdateProfile(action, updateuser, uusrerrMsgs);
		assertTrue(firstNameError.equals(uusrerrMsgs.getFirstNameError()));
		assertTrue(lastNameError.equals(uusrerrMsgs.getLastNameError()));
		assertTrue(passwordError.equals(uusrerrMsgs.getPasswordError()));
		assertTrue(confirmPasswordError.equals(uusrerrMsgs.getConfirmPasswordError()));
		assertTrue(phoneError.equals(uusrerrMsgs.getPhoneError()));
		assertTrue(dobError.equals(uusrerrMsgs.getDobError()));
		assertTrue(ageError.equals(uusrerrMsgs.getAgeError()));
		assertTrue(countryError.equals(uusrerrMsgs.getCountryError()));
		assertTrue(addressError.equals(uusrerrMsgs.getAddressError()));
		assertTrue(cityError.equals(uusrerrMsgs.getCityError()));
		assertTrue(stateError.equals(uusrerrMsgs.getStateError()));
		assertTrue(pinError.equals(uusrerrMsgs.getPinError()));
		assertTrue(dlExpError.equals(uusrerrMsgs.getDlExpError()));
		assertTrue(dlCountryError.equals(uusrerrMsgs.getDlCountryError()));
		assertTrue(errorMsg.equals(uusrerrMsgs.getErrorMsg()));
	}
}
