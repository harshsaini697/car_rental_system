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

import org.junit.*;

@RunWith(JUnitParamsRunner.class)

public class RentalTest {
	Rental rental;
	RentalErrorMsgs rtlerrMsgs;
	
	@Before
    public void setUp() throws Exception{
    	rental = new Rental();
    	rtlerrMsgs = new RentalErrorMsgs();
    }
	int i = 0;
	@Test
	@FileParameters("csv/RentalTest.csv")
	
	public void test(String testcaseNo, String action, String startdate, String enddate, String occupants, String starttime, String endtime, String nameoncard, String billingaddress, String ccno, String cvv, String username, String carname, String capacity, String weekdayrate, String weekendrate, String weekrate, String gpsrate, String onstarrate, String siriusxmrate, String startDateError, String endDateError, String occupantsError, String timeError, String confirmRentalError, String addRentalError, String addCarError, String start, String end, long diff, int weekend, String errorMsg) {
		rental.setRental(startdate, enddate, occupants, starttime, endtime, nameoncard, billingaddress, ccno, cvv, username, carname, capacity, weekdayrate, weekendrate, weekrate, gpsrate, onstarrate, siriusxmrate);
		rental.validateRental(action, rental, rtlerrMsgs);
		
		assertTrue(startDateError.equals(rtlerrMsgs.getStartDateError()));
		assertTrue(endDateError.equals(rtlerrMsgs.getEndDateError()));
		assertTrue(occupantsError.equals(rtlerrMsgs.getOccupantsError()));
		assertTrue(timeError.equals(rtlerrMsgs.getTimeError()));
		assertTrue(confirmRentalError.equals(rtlerrMsgs.getConfirmRentalError()));
		assertTrue(addRentalError.equals(rtlerrMsgs.getAddRentalError()));
		assertTrue(addCarError.equals(rtlerrMsgs.getAddCarError()));
		assertTrue(errorMsg.equals(rtlerrMsgs.getErrorMsg()));
		assertEquals(diff,(rental.getDayCount(start, end)));
		assertEquals(weekend,(rental.getNumberOfWeekEnds(start, end)));
		System.out.println("1"+rtlerrMsgs.getStartDateError()+"2"+rtlerrMsgs.getEndDateError()+"3"+rtlerrMsgs.getOccupantsError()+"4"+rtlerrMsgs.getTimeError()+"5"+rtlerrMsgs.getConfirmRentalError()+"6"+rtlerrMsgs.getAddRentalError()+"7"+rtlerrMsgs.getAddCarError());
		//i++;
		//System.out.println(i+" "+rtlerrMsgs.getAddCarError());
	}
}
