package mavride.data;

import java.sql.Connection;
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

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;

import mavride.model.User;
import mavride.util.SQLConnection;

public class UsersDAO{

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<User> ReturnMatchingUsersList (String queryString){
		ArrayList<User> userListInDB = new ArrayList<User>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
	try {
		stmt = conn.createStatement();
		ResultSet userList = stmt.executeQuery(queryString);
		while (userList.next()) {
			User user = new User(); 
			user.setPrefix(userList.getString("prefix"));
			user.setUsername(userList.getString("username"));
			user.setFirstname(userList.getString("firstname"));
			user.setLastname(userList.getString("lastname"));
			user.setUtaid(userList.getString("utaid"));
			user.setEmail(userList.getString("email"));
			user.setPassword(userList.getString("password"));
			user.setPhone(userList.getString("phone"));
			user.setDob(userList.getString("dob"));
			user.setAge(userList.getString("age"));
			user.setCountry(userList.getString("country"));
			user.setAddress(userList.getString("address"));
			user.setCity(userList.getString("city"));
			user.setState(userList.getString("state"));
			user.setPin(userList.getString("pin"));
			user.setDlnumber(userList.getString("dlnumber"));
			user.setDlexp(userList.getString("dlexp"));
			user.setDlcountry(userList.getString("dlcountry"));
			user.setAacm(userList.getString("aacm"));
			user.setUsertype(userList.getString("usertype"));
			user.setUserrole(userList.getString("userrole")); 
			userListInDB.add(user);	
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
	return userListInDB;
	}
	
	public static void StoreUserInDB (User user,String queryString){
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			
			String insertUser = queryString + " VALUES ('"  
					+ user.getPrefix()  + "','"
					+ user.getFirstname() + "','"		
					+ user.getLastname() + "','"
					+ user.getUtaid() + "','"
					+ user.getUsername() + "','"
					+ user.getEmail() + "','"
					+ user.getPassword() + "','"
					+ user.getPhone() + "','"
					+ user.getDob() + "','"
					+ user.getAge() + "','"
					+ user.getCountry() + "','"
					+ user.getAddress() + "','"
					+ user.getCity() + "','"
					+ user.getState() + "','"
					+ user.getPin() + "','"
					+ user.getDlnumber() + "','"
					+ user.getDlexp() + "','"
					+ user.getDlcountry() + "','"
					+ user.getAacm() + "','"
					+ user.getUsertype() + "','"
					+ user.getUserrole() + "','"
					+ user.getStatus() + "')";
			stmt.executeUpdate(insertUser);	
			conn.commit(); 
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}};
			
	}
	public static void insertUser(User user){  
		StoreUserInDB(user,"INSERT INTO USERS (prefix, firstname, lastname, utaid, username, email, password, phone, dob, age, country, address, city, state, pin, dlnumber, dlexp, dlcountry, aacm, usertype, userrole, status) ");
	} 
	public static Boolean UserNameunique(String username)  {  
		return (ReturnMatchingUsersList(" SELECT * from users WHERE username = '"+username+"' ORDER BY utaid").isEmpty());
	}
	public static Boolean UserRoleunique(String userrole, String utaid)  {  
		return (ReturnMatchingUsersList(" SELECT * from users WHERE utaid = '"+utaid+"' and userrole = '"+userrole+"' ORDER BY utaid").isEmpty());
	}
	public static Boolean Password(String password) {
		return (ReturnMatchingUsersList(" SELECT * from users WHERE password = '"+password+"'").isEmpty());
	}
	public static ArrayList<User> getProfile(String username) {  
		return ReturnMatchingUsersList(" SELECT * from users WHERE username = '"+username+"'");
}
}
