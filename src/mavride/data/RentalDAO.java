package mavride.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mavride.model.Cars;
import mavride.model.MyReservations;
import mavride.model.User;
import mavride.util.SQLConnection;

public class RentalDAO {
	public static int id=0;
	public static void StoreRentalInDB (String username, String name, String carname, float baseprice, float taxapplied, float discount, String startdate, String starttime, String enddate, String endtime, float totalamount, String nameoncard, String billingaddress, String ccno, String cctype, String expdate, String cvvno){
		
		Connection conn = SQLConnection.getDBConnection();  
		try {
			PreparedStatement ps = conn.prepareStatement("insert into rentals (username,name,carname,baseprice,taxapplied,discount,startdate,starttime,enddate,endtime,totalamount,nameoncard,billingaddress,ccno,cctype,expdate,cvvno)values('"+username+"','"+name+"','"+carname+"','"+baseprice+"','"+taxapplied+"','"+discount+"','"+startdate+"','"+starttime+"','"+enddate+"','"+endtime+"','"+totalamount+"','"+nameoncard+"','"+billingaddress+"','"+ccno+"','"+cctype+"','"+expdate+"','"+cvvno+ "')",Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
            
            if(rs.next()){
                id=rs.getInt(1);
            }
			
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
	
	public static ArrayList<String> GetReservationSummary (){
		ArrayList<String> lastInsertedRentalinDB = new ArrayList<String>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		System.out.println("id milrelai = "+id);
		ResultSet lastinsertedrental = stmt.executeQuery("select * from rentals where rentalid="+id);
		while (lastinsertedrental.next()) {			
			lastInsertedRentalinDB.add(lastinsertedrental.getString("rentalid"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("username"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("name"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("carname"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("baseprice"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("taxapplied"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("discount"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("startdate"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("starttime"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("enddate"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("endtime"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("totalamount"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("nameoncard"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("billingaddress"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("ccno"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("cctype"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("expdate"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("cvvno"));			
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
	return lastInsertedRentalinDB;
	}
	
	public static void deleteRental(int rentalid) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from rentals where rentalid="+rentalid);
			ps.executeUpdate();
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
		
	}
	
	public static ArrayList<String> GetReservationDetails (int rentalid){
		ArrayList<String> lastInsertedRentalinDB = new ArrayList<String>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet lastinsertedrental = stmt.executeQuery("select * from rentals where rentalid="+rentalid);
		while (lastinsertedrental.next()) {			
			lastInsertedRentalinDB.add(lastinsertedrental.getString("rentalid"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("username"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("name"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("carname"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("baseprice"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("taxapplied"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("discount"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("startdate"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("starttime"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("enddate"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("endtime"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("totalamount"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("nameoncard"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("billingaddress"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("ccno"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("cctype"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("expdate"));
			lastInsertedRentalinDB.add(lastinsertedrental.getString("cvvno"));			
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
	return lastInsertedRentalinDB;
	}
	
	public static ArrayList<MyReservations> getMyReservations (String username){
		ArrayList<MyReservations> myRentalsinDB = new ArrayList<MyReservations>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet rentalsList = stmt.executeQuery("select rentalid,name,carname,startdate,starttime,enddate,endtime,totalamount from rentals where username='"+username+"'");
		while (rentalsList.next()) {
			MyReservations myres = new MyReservations(); 
			myres.setRentalid(rentalsList.getString("rentalid"));
			myres.setName(rentalsList.getString("name"));
			myres.setCarname(rentalsList.getString("carname"));
			myres.setStartdate(rentalsList.getString("startdate"));
			myres.setStarttime(rentalsList.getString("starttime"));
			myres.setEnddate(rentalsList.getString("enddate"));
			myres.setEndtime(rentalsList.getString("endtime"));
			myres.setTotalamount(rentalsList.getString("totalamount"));
			myRentalsinDB.add(myres);	
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
	return myRentalsinDB;
	}
	
	public static ArrayList<MyReservations> getAllReservations (){
		ArrayList<MyReservations> myRentalsinDB = new ArrayList<MyReservations>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet rentalsList = stmt.executeQuery("select rentalid,name,carname,startdate,starttime,enddate,endtime,totalamount from rentals");
		while (rentalsList.next()) {
			MyReservations myres = new MyReservations(); 
			myres.setRentalid(rentalsList.getString("rentalid"));
			myres.setName(rentalsList.getString("name"));
			myres.setCarname(rentalsList.getString("carname"));
			myres.setStartdate(rentalsList.getString("startdate"));
			myres.setStarttime(rentalsList.getString("starttime"));
			myres.setEnddate(rentalsList.getString("enddate"));
			myres.setEndtime(rentalsList.getString("endtime"));
			myres.setTotalamount(rentalsList.getString("totalamount"));
			myRentalsinDB.add(myres);	
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
	return myRentalsinDB;
	}
	
	public static ArrayList<MyReservations> getAllReservationsonDate (String date){
		ArrayList<MyReservations> myRentalsinDB = new ArrayList<MyReservations>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet rentalsList = stmt.executeQuery("select rentalid,name,carname,startdate,starttime,enddate,endtime,totalamount from rentals where startdate='"+date+"'");
		while (rentalsList.next()) {
			MyReservations myres = new MyReservations(); 
			myres.setRentalid(rentalsList.getString("rentalid"));
			myres.setName(rentalsList.getString("name"));
			myres.setCarname(rentalsList.getString("carname"));
			myres.setStartdate(rentalsList.getString("startdate"));
			myres.setStarttime(rentalsList.getString("starttime"));
			myres.setEnddate(rentalsList.getString("enddate"));
			myres.setEndtime(rentalsList.getString("endtime"));
			myres.setTotalamount(rentalsList.getString("totalamount"));
			myRentalsinDB.add(myres);	
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
	return myRentalsinDB;
	}
}
