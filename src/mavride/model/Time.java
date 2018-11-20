package mavride.model;

import java.io.Serializable;
import mavride.data.CarsDAO;

public class Time implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private String weekday = "";
	private String saturday = "";
	private String sunday = "";
	
	public void setTime (String weekday, String saturday,String sunday) {
		setWeekday(weekday);
		setSaturday(saturday);
		setSunday(sunday);
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getSaturday() {
		return saturday;
	}

	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}

	public String getSunday() {
		return sunday;
	}

	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	
	
}