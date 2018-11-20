package mavride.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

import mavride.data.CarsDAO;
import mavride.data.UsersDAO;

public class Rental implements Serializable{
	private static final long serialVersionUID = 3L;
	private String startdate;
	private String enddate;
	private String occupants;
	private String starttime;
	private String endtime;
	private String nameoncard;
	private String billingaddress;
	private String ccno;
	private String cvv;
	private String username;
	private String carname;
	private String capacity;
	private String weekdayrate;
	private String weekendrate;
	private String weekrate;
	private String gpsrate;
	private String onstarrate;
	private String siriusxmrate;
	
	
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static long getDayCount(String start, String end) {
		long diff;
		if(start.equals("")) {
			diff = 0;
			return diff;
		}
		else {
			diff = -1;
			try {
			    Date dateStart = simpleDateFormat.parse(start);
			    Date dateEnd = simpleDateFormat.parse(end);

			    //time is always 00:00:00 so rounding should help to ignore the missing hour when going from winter to summer time as well as the extra hour in the other direction
			    diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
			  } catch (Exception e) {
			    //handle the exception according to your own situation
			  }
		}
		  
		  
		  return diff;
	}
	
	public static int getNumberOfWeekEnds(String start,String end){
		int WeekEnds = 0;		
		try {
			Date dateStart = simpleDateFormat.parse(start);
		    Date dateEnd = simpleDateFormat.parse(end);
		    
		    Calendar c1 = Calendar.getInstance();
		    c1.clear();
		    c1.setTime(dateStart);
		    Calendar c2 = Calendar.getInstance();
		    c2.clear();
		    c2.setTime(dateEnd);
		    
		    
		    while(c2.after(c1)) {
		    	if(c1.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY||c1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||c1.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		    	WeekEnds++;
		    	else {}
		    	c1.add(Calendar.DATE,1);
		    	
		    	}
		    
		    if(c2.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY|| c2.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY|| c2.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY) {
		    	WeekEnds++;
			}
		    	
		
		
		}catch(Exception e) {
			
		}
		//System.out.println(WeekEnds);
    	return WeekEnds;

		}
	
	public void setRental (String startdate, String enddate, String occupants, String starttime, String endtime, String nameoncard, String billingaddress, String ccno, String cvv, String username, String carname, String capacity, String weekdayrate, String weekendrate, String weekrate, String gpsrate, String onstarrate, String siriusxmrate) {
		setStartdate(startdate);
		setEnddate(enddate);
		setOccupants(occupants);
		setStarttime(starttime);
		setEndtime(endtime);
		setNameoncard(nameoncard);
		setBillingaddress(billingaddress);
		setCcno(ccno);
		setCvv(cvv);
		setUsername(username);
		setCarname(carname);
		setCapacity(capacity);
		setWeekdayrate(weekdayrate);
		setWeekendrate(weekendrate);
		setWeekrate(weekrate);
		setGpsrate(gpsrate);
		setOnstarrate(onstarrate);
		setSiriusxmrate(siriusxmrate);
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public void validateRental(String action, Rental rental, RentalErrorMsgs errorMsgs) {
		if(action.equals("reserveRental")) {
			errorMsgs.setStartDateError(validateStartDate(action,rental.getStartdate()));
			errorMsgs.setEndDateError(validateEndDate(action, rental.getEnddate(), rental.getStartdate()));
			errorMsgs.setOccupantsError(validateOccupants(action, rental.getOccupants()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("listCars")) {
			errorMsgs.setTimeError(validateTime(action, rental.getStarttime(), rental.getEndtime()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("confirmRental")) {
			errorMsgs.setConfirmRentalError(validateConfirmRentalError(action, rental.getNameoncard(), rental.getBillingaddress(), rental.getCcno(), rental.getCvv()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("addRental")) {
			errorMsgs.setAddRentalError(validateAddRentalError(action, rental.getUsername()));
			errorMsgs.setErrorMsg(action);
		}
		else if(action.equalsIgnoreCase("addCar1")) {
			errorMsgs.setAddCarError(validateAddCarError(action, rental.getCarname(), rental.getCapacity(), rental.getWeekdayrate(), rental.getWeekendrate(), rental.getWeekrate(), rental.getGpsrate(), rental.getOnstarrate(), rental.getSiriusxmrate()));
			errorMsgs.setErrorMsg(action);
		}
		else {
			
		}
	}
	
	public String validateAddCarError(String action, String carname, String capacity, String weekdayrate, String weekendrate, String weekrate, String gpsrate, String onstarrate, String siriusxmrate) {
		String result = "";
		
			carname = carname.trim();
			if(carname.equals(""))
				result = "Carname cannot be blank";
			else if(!stringSize(carname,3,45))
				result = "Carname must be between 3 and 45 characters";
			else if(!carname.matches("^[a-zA-Z0-9-]+$"))
				result = "Carname must contain only characters numbers and hyphens";
			else if(!CarsDAO.CarNameunique(carname))
				result = "Carname already exists";
			else if(capacity.equals(""))
				result = "Capacity cannot be blank";
			else if(Integer.parseInt(capacity) < 1)
				result = "Capacity cannot be less than 1";
			else if(Integer.parseInt(capacity) > 18)
				result = "Capacity cannot be more than 18";
			else if(weekdayrate.equals(""))
				result = "Weekdayrate cannot be blank";
			else if(!weekdayrate.matches("^[0-9]*\\.[0-9]{2}$"))
				result = "Weekdayrate must contain only float values upto 2 decimals";
			else if(Float.parseFloat(weekdayrate) < 1)
				result = "Weekdayrate cannot be less than 1.00";
			else if(Float.parseFloat(weekdayrate) > 500)
				result = "Weekdayrate cannot be more than 500.00";

			else if(weekendrate.equals(""))
				result = "weekendrate cannot be blank";
			else if(!weekendrate.matches("^[0-9]*\\.[0-9]{2}$"))
				result = "weekendrate must contain only float values upto 2 decimals";
			else if(Float.parseFloat(weekendrate) < 1)
				result = "weekendrate cannot be less than 1.00";
			else if(Float.parseFloat(weekendrate) > 500)
				result = "weekendrate cannot be more than 500.00";
			
			else if(weekrate.equals(""))
				result = "weekrate cannot be blank";
			else if(!weekrate.matches("^[0-9]*\\.[0-9]{2}$"))
				result = "weekrate must contain only float values upto 2 decimals";
			else if(Float.parseFloat(weekrate) < 1)
				result = "weekrate cannot be less than 1.00";
			else if(Float.parseFloat(weekrate) > 5000)
				result = "weekrate cannot be more than 5000.00";
			
			else if(gpsrate.equals(""))
				result = "gpsrate cannot be blank";
			else if(!gpsrate.matches("^[0-9]*\\.[0-9]{2}$"))
				result = "gpsrate must contain only float values upto 2 decimals";
			else if(Float.parseFloat(gpsrate) < 1)
				result = "gpsrate cannot be less than 1.00";
			else if(Float.parseFloat(gpsrate) > 10)
				result = "gpsrate cannot be more than 10.00";
			
			else if(onstarrate.equals(""))
				result = "onstarrate cannot be blank";
			else if(!onstarrate.matches("^[0-9]*\\.[0-9]{2}$"))
				result = "onstarrate must contain only float values upto 2 decimals";
			else if(Float.parseFloat(onstarrate) < 1)
				result = "onstarrate cannot be less than 1.00";
			else if(Float.parseFloat(onstarrate) > 10)
				result = "onstarrate cannot be more than 10.00";
			
			else if(siriusxmrate.equals(""))
				result = "siriusxmrate cannot be blank";
			else if(!siriusxmrate.matches("^[0-9]*\\.[0-9]{2}$"))
				result = "siriusxmrate must contain only float values upto 2 decimals";
			else if(Float.parseFloat(siriusxmrate) < 1)
				result = "siriusxmrate cannot be less than 1.00";
			else if(Float.parseFloat(siriusxmrate) > 10)
				result = "siriusxmrate cannot be more than 10.00";
			
			return result;
		
	}
	
	public String validateAddRentalError(String action, String username) {
		String result = "";
		
			username = username.trim();
			if(username.equalsIgnoreCase(""))
				result = "Username cannot be blank";
			else if(UsersDAO.UserNameunique(username))
				result = "Username does not exist";
			return result;
		
	}
	
	public String validateConfirmRentalError(String action, String nameoncard, String billingaddress, String ccno, String cvv) {
		String result = "";
		
			nameoncard = nameoncard.trim();
			billingaddress = billingaddress.trim();
			ccno = ccno.trim();
			cvv = cvv.trim();
			if(nameoncard.equals(""))
				result = "Name cannot be blank";
			else if(!stringSize(nameoncard,3,45))
				result = "Name must be between 3 and 45 characters";
			else if(!nameoncard.matches("^[a-zA-Z- ]+$"))
				result = "Name must contain only characters and spaces";
			else if(billingaddress.equals(""))
				result = "Address cannot be blank";
			else if(!stringSize(billingaddress,3,45))
				result = "Address must be between 3 and 45 characters";
			else if(!billingaddress.matches("^[0-9a-zA-Z- '/,]+$"))
				result = "Address must contain only characters hyphens spaces";
			else if(ccno.equals(""))
				result = "CC Number cannot be blank";
			else if(ccno.length() != 16)
				result = "Invalid CC Number";
			else if(cvv.equals(""))
				result = "CVV Number cannot be blank";
			else if(!stringSize(cvv,3,4))
				result = "Invalid CVV Number";
			return result;
		
	}
	
	private boolean stringSize(String string, int min, int max) {
		return string.length()>=min && string.length()<=max;
	}
	
	public String validateTime(String action, String starttime, String endtime) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
			Date time1 = sdf.parse(starttime);
			Date time2 = sdf.parse(endtime);
			if(time1.compareTo(time2) > 0) {
				result = "End time cannot be before start time";
			}
			else if(time1.compareTo(time2) == 0) {
				result = "Start time and End time cannot be same";
			}
		}
		catch(Exception e) {
		}
		return result;
		
	}
	
	public String validateOccupants(String action, String occupants) {
		String result = "";
		
			//int occupantsint = Integer.parseInt(occupants);
			if(occupants.equals("")) {
				result = "Number of occupants cannot be blank";
			}
			else if(Integer.parseInt(occupants) < 1) {
				result = "Number of occupants cannot be less than 1";
			}
			else if(Integer.parseInt(occupants) > 9) {
				result = "Number of occupants cannot be more than 9";
			}
		
		return result;
	}
	
	public String validateStartDate(String action, String startdate) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			Date date1 = sdf.parse(startdate);
			Date date2 = sdf.parse(dtf.format(localDate));
			if(date1.compareTo(date2) < 0)
				result = "Date is in the past";	
			else
				result = "";
		}
		catch(Exception e) {
			result = "Invalid date format or date cannot be blank";
		}
		return result;
		
	}
	
	public String validateEndDate(String action, String enddate, String startdate) {
		String result = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			Date date1 = sdf.parse(enddate);
			Date date3 = sdf.parse(startdate);
			Date date2 = sdf.parse(dtf.format(localDate));
			if(date1.compareTo(date2) < 0)
				result = "Date is in the past";	
			else if(date1.compareTo(date3) < 0) {
				result = "End date cannot be before start date";
			}
			else
				result = "";
		}
		catch(Exception e) {
			result = "Invalid date format or date cannot be blank";
		}
		return result;
		
	}

	public String getOccupants() {
		return occupants;
	}

	public void setOccupants(String occupants) {
		this.occupants = occupants;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getNameoncard() {
		return nameoncard;
	}

	public void setNameoncard(String nameoncard) {
		this.nameoncard = nameoncard;
	}

	public String getBillingaddress() {
		return billingaddress;
	}

	public void setBillingaddress(String billingaddress) {
		this.billingaddress = billingaddress;
	}

	public String getCcno() {
		return ccno;
	}

	public void setCcno(String ccno) {
		this.ccno = ccno;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getWeekdayrate() {
		return weekdayrate;
	}

	public void setWeekdayrate(String weekdayrate) {
		this.weekdayrate = weekdayrate;
	}

	public String getWeekendrate() {
		return weekendrate;
	}

	public void setWeekendrate(String weekendrate) {
		this.weekendrate = weekendrate;
	}

	public String getWeekrate() {
		return weekrate;
	}

	public void setWeekrate(String weekrate) {
		this.weekrate = weekrate;
	}

	public String getGpsrate() {
		return gpsrate;
	}

	public void setGpsrate(String gpsrate) {
		this.gpsrate = gpsrate;
	}

	public String getOnstarrate() {
		return onstarrate;
	}

	public void setOnstarrate(String onstarrate) {
		this.onstarrate = onstarrate;
	}

	public String getSiriusxmrate() {
		return siriusxmrate;
	}

	public void setSiriusxmrate(String siriusxmrate) {
		this.siriusxmrate = siriusxmrate;
	}
}
