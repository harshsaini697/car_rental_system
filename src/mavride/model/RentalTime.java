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

import mavride.data.UsersDAO;

public class RentalTime implements Serializable{
	private static final long serialVersionUID = 3L;
	private String starttime = "";
	private String endtime = "";
	
	public void setRentalTime (String starttime, String endtime) {
		setStarttime(starttime);
		setEndtime(endtime);
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
	
}
