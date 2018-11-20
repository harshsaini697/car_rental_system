package mavride.model;

import java.io.Serializable;

public class MyReservations implements Serializable{
	private static final long serialVersionUID = 3L;
	private String rentalid = "";
	private String name = "";
	private String carname = "";
	private String startdate = "";
	private String starttime = "";
	private String enddate = "";
	private String endtime = "";
	private String totalamount = "";
	
	public void setMyReservations(String rentalid, String name, String carname, String startdate, String starttime, String enddate, String endtime, String totalamount) {
		setRentalid(rentalid);
		setName(name);
		setCarname(carname);
		setStartdate(startdate);
		setStarttime(starttime);
		setEnddate(enddate);
		setEndtime(endtime);
		setTotalamount(totalamount);
	}

	public String getRentalid() {
		return rentalid;
	}

	public void setRentalid(String rentalid) {
		this.rentalid = rentalid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}
}
