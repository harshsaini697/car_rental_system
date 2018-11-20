package mavride.model;

import java.io.Serializable;
import mavride.data.CarsDAO;

public class Cars implements Serializable{
	
	private static final long serialVersionUID = 3L;
	private String carname = "";
	private String capacity = "";
	private String weekdayrate = "";
	private String weekendrate = "";
	private String weekrate = "";
	private String gpsrate = "";
	private String onstarrate = "";
	private String siriusxmrate = "";
	
	public void setCars (String carname, String capacity,String weekdayrate, String weekendrate, String weekrate, String gpsrate, String onstarrate, String siriusxmrate) {
		setCarname(carname);
		setCapacity(capacity);
		setWeekdayrate(weekdayrate);
		setWeekendrate(weekendrate);
		setWeekrate(weekrate);
		setGpsrate(gpsrate);
		setOnstarrate(onstarrate);
		setSiriusxmrate(siriusxmrate);
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
