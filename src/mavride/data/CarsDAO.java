package mavride.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mavride.model.Cars;
import mavride.model.Time;
import mavride.model.User;
import mavride.util.SQLConnection;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;

public class CarsDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Cars> ReturnMatchingCarsList (String queryString){
		ArrayList<Cars> carsListInDB = new ArrayList<Cars>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet carsList = stmt.executeQuery(queryString);
		while (carsList.next()) {
			Cars cars = new Cars(); 
			cars.setCarname(carsList.getString("carname"));
			cars.setCapacity(carsList.getString("capacity"));
			cars.setWeekdayrate(carsList.getString("weekdayrate"));
			cars.setWeekendrate(carsList.getString("weekendrate"));
			cars.setWeekrate(carsList.getString("weekrate"));
			cars.setGpsrate(carsList.getString("gpsrate"));
			cars.setOnstarrate(carsList.getString("onstarrate"));
			cars.setSiriusxmrate(carsList.getString("siriusxmrate"));
			carsListInDB.add(cars);	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return carsListInDB;
	}
	
	private static ArrayList<String> ReturnWeekday (String queryString){
		ArrayList<String> timeListinDB = new ArrayList<String>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet timeList = stmt.executeQuery(queryString);
		while (timeList.next()) {			
			timeListinDB.add(timeList.getString("weekday"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return timeListinDB;
	}
	
	private static ArrayList<String> ReturnSaturday (String queryString){
		ArrayList<String> timeListinDB = new ArrayList<String>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet timeList = stmt.executeQuery(queryString);
		while (timeList.next()) {
			
			timeListinDB.add(timeList.getString("saturday"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return timeListinDB;
	}
	
	private static ArrayList<String> ReturnSunday (String queryString){
		ArrayList<String> timeListinDB = new ArrayList<String>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet timeList = stmt.executeQuery(queryString);
		while (timeList.next()) {
			timeListinDB.add(timeList.getString("sunday"));	
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return timeListinDB;
	}
	
	public static String ReturnExtras (String queryString){
		String extrasinDB = "";
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		System.out.println(queryString);
	try {
		stmt = conn.createStatement();
		ResultSet extrasList = stmt.executeQuery(queryString);
		while (extrasList.next()) {
			extrasinDB = extrasList.getString(1);
			System.out.println(extrasList.getString(1));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return extrasinDB;
	}
	
	private static ArrayList<String> ReturnPrice (String queryString){
		ArrayList<String> priceinDB = new ArrayList<String>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet priceList = stmt.executeQuery(queryString);
		while (priceList.next()) {
			priceinDB.add(priceList.getString("weekdayrate"));	
			priceinDB.add(priceList.getString("weekendrate"));
			priceinDB.add(priceList.getString("weekrate"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	return priceinDB;
	}
	
	public static void addCar (String carname, String capacity, String weekdayrate, String weekendrate, String weekrate, String gpsrate, String onstarrate, String siriusxmrate){
		
		Connection conn = SQLConnection.getDBConnection();  
		try {
			PreparedStatement ps = conn.prepareStatement("insert into cars (carname,capacity,weekdayrate,weekendrate,weekrate,gpsrate,onstarrate,siriusxmrate)values('"+carname+"','"+capacity+"','"+weekdayrate+"','"+weekendrate+"','"+weekrate+"','"+gpsrate+"','"+onstarrate+"','"+siriusxmrate+"')");
			ps.executeUpdate();
			conn.commit(); 
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}};
			
	}
	
public static void deleteCar (String carname){
		
		Connection conn = SQLConnection.getDBConnection();  
		try {
			PreparedStatement ps = conn.prepareStatement("delete from cars where carname='"+carname+"'");
			ps.executeUpdate();
			conn.commit(); 
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}};
			
	}
	
	public static String listGPS(String carname) {  
		return ReturnExtras("SELECT gpsrate from cars where carname='"+carname+"'");
	}
	public static String listOnStar(String carname) {  
		return ReturnExtras("SELECT onstarrate from cars where carname='"+carname+"'");
	}
	public static String listSiriusXM(String carname) {  
		return ReturnExtras("SELECT siriusxmrate from cars where carname='"+carname+"'");
	}
	public static ArrayList<String> getPrice(String carname) {  
		return ReturnPrice("SELECT weekdayrate,weekendrate,weekrate from cars where carname='"+carname+"'");
	}
	
	public static ArrayList<Cars> listCars(int occupants, String startdate) {  
		return ReturnMatchingCarsList("SELECT distinct cars.carname,cars.capacity,cars.weekdayrate,cars.weekendrate,cars.weekrate,cars.gpsrate,cars.onstarrate, cars.siriusxmrate from cars,rentals where capacity >="+occupants+" and rentals.carname=cars.carname and'"+startdate+"' not between rentals.startdate and rentals.enddate");
		//return ReturnMatchingCarsList("select * from cars");
	}
	
	public static ArrayList<Cars> listAllCars() {  
		return ReturnMatchingCarsList("SELECT * from cars");
	}
	
	public static ArrayList<String> listTimeSaturday() {  
		return ReturnSaturday("SELECT saturday from days where saturday is not null and saturday <> ''");
	}
	
	public static ArrayList<String> listTimeSunday() {  
		return ReturnSunday("SELECT distinct sunday from days where sunday is not null and sunday <> ''");
	}
	public static ArrayList<String> listTimeWeekday() {  
		return ReturnWeekday("SELECT weekday from days");
	}
	public static Boolean CarNameunique(String carname)  {  
		return (ReturnMatchingCarsList(" SELECT * from cars WHERE carname = '"+carname+"'").isEmpty());
	}
	
}
