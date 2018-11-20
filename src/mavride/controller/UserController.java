package mavride.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mavride.data.UsersDAO;
import mavride.model.User;
import mavride.model.UserErrorMsgs;
import mavride.model.UpdateUser;
import mavride.model.UpdateUserErrorMsgs;
import mavride.controller.*;
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private void getUserParam (HttpServletRequest request, User user) {
		user.setUser(request.getParameter("prefix"), request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("utaid"), request.getParameter("username"), request.getParameter("email"),request.getParameter("cnfemail"), request.getParameter("password"),request.getParameter("cnfpassword"), request.getParameter("phone"), request.getParameter("dob"), request.getParameter("age"), request.getParameter("country"), request.getParameter("address"), request.getParameter("city"), request.getParameter("state"), request.getParameter("pin"), request.getParameter("dlnumber"), request.getParameter("dlexp"), request.getParameter("dlcountry"), request.getParameter("aacm"), request.getParameter("usertype"), request.getParameter("userrole"), request.getParameter("status"));  
	}
	private void getUpdateParam (HttpServletRequest request, UpdateUser updateuser) {
		updateuser.setUpdateUser(request.getParameter("prefix"), request.getParameter("firstname"), request.getParameter("lastname"), request.getParameter("password"),request.getParameter("cnfpassword"), request.getParameter("phone"), request.getParameter("dob"), request.getParameter("age"), request.getParameter("country"), request.getParameter("address"), request.getParameter("city"), request.getParameter("state"), request.getParameter("pin"), request.getParameter("dlexp"), request.getParameter("dlcountry"), request.getParameter("aacm"), request.getParameter("usertype"));  
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		User user = new User();
		UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
		UpdateUser updateuser = new UpdateUser();
		UpdateUserErrorMsgs UUerrorMsgs = new UpdateUserErrorMsgs();
		
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("listProfile")) {
			Cookie[] cookies = request.getCookies();
			int i = 0;
			for (Cookie cookie : cookies ) {

			  System.out.println(cookies[i].getName());
			  System.out.println(cookies[i].getValue());
			  session.setAttribute("username", cookies[1].getValue());
			  i++;
			}
			ArrayList<User> profileInDB = new ArrayList<User>();
			String username = (String) session.getAttribute("username");
			profileInDB = UsersDAO.getProfile(username);
			session.setAttribute("profile", profileInDB);
			getServletContext().getRequestDispatcher("/updateprofile.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("editupdatedProfile")) {
			ArrayList<User> profileInDB = new ArrayList<User>();
			String username = (String) session.getAttribute("username");
			profileInDB = UsersDAO.getProfile(username);
			session.setAttribute("profile", profileInDB);
			session.removeAttribute("username");
			Cookie[] cookies = request.getCookies();
			int i = 0;
			for (Cookie cookie : cookies ) {

			  System.out.println(cookies[i].getName());
			  System.out.println(cookies[i].getValue());
			  session.setAttribute("username", cookies[1].getValue());
			  i++;
			}
			getServletContext().getRequestDispatcher("/edituser2.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("updatedProfile")) {
			ArrayList<User> profileInDB = new ArrayList<User>();
			String username = (String) session.getAttribute("username");
			profileInDB = UsersDAO.getProfile(username);
			session.setAttribute("profile", profileInDB);
			getServletContext().getRequestDispatcher("/updatedprofile.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("editUser")) {
			getUserParam(request,user);
			user.validateUser(action,user,UerrorMsgs);
			session.setAttribute("user", user);
			if(!UerrorMsgs.getErrorMsg().equals("")) {
				getUserParam(request,user);
				session.setAttribute("errorMsgs", UerrorMsgs);
				response.sendRedirect("edituser.jsp");
			}
			else {
				ArrayList<User> profileInDB = new ArrayList<User>();
				String username = request.getParameter("username");
				session.setAttribute("username", username);
				profileInDB = UsersDAO.getProfile(username);
				session.setAttribute("profile", profileInDB);
				getServletContext().getRequestDispatcher("/edituser1.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("revokeUser")) {
			getUserParam(request,user);
			user.validateUser(action,user,UerrorMsgs);
			session.setAttribute("user", user);
			if(!UerrorMsgs.getErrorMsg().equals("")) {
				getUserParam(request,user);
				session.setAttribute("errorMsgs", UerrorMsgs);
				response.sendRedirect("revokeuser.jsp");
			}
			else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavride?autoReconnect=true&useSSL=false", "root", "intel1");
					PreparedStatement ps = conn.prepareStatement("update users set status='Revoked' where username=?");
					ps.setString(1,request.getParameter("username"));
					ps.executeUpdate();
					session.setAttribute("message", "Username "+request.getParameter("username")+" has been revoked successfully");
					getServletContext().getRequestDispatcher("/revokeuser.jsp").forward(request, response);
				} 
				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if(action.equalsIgnoreCase("enableUser")) {
			getUserParam(request,user);
			user.validateUser(action,user,UerrorMsgs);
			session.setAttribute("user", user);
			if(!UerrorMsgs.getErrorMsg().equals("")) {
				getUserParam(request,user);
				session.setAttribute("errorMsgs", UerrorMsgs);
				response.sendRedirect("revokeuser.jsp");
			}else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavride?autoReconnect=true&useSSL=false", "root", "intel1");
					PreparedStatement ps = conn.prepareStatement("update users set status='Enabled' where username=?");
					ps.setString(1,request.getParameter("username"));
					ps.executeUpdate();
					session.setAttribute("message", "Username "+request.getParameter("username")+" has been enabled successfully");
					getServletContext().getRequestDispatcher("/enableuser.jsp").forward(request, response);
				} 
				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else if(action.equalsIgnoreCase("editupdateUser")) {
			try {
				System.out.println("idar tak ayelai bawa");
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavride?autoReconnect=true&useSSL=false", "root", "intel1");
				PreparedStatement ps = conn.prepareStatement("update users set prefix=?, firstname=?, lastname=?, password=?, phone=?, dob=?, age=?, country=?, address=?, city=?, state=?, pin=?, dlexp=?, dlcountry=?, aacm=?, usertype=? where username=?");
				ps.setString(1,request.getParameter("prefix"));
				ps.setString(2,request.getParameter("firstname"));
				ps.setString(3,request.getParameter("lastname"));
				ps.setString(4,request.getParameter("password"));
				ps.setString(5,request.getParameter("phone"));
				ps.setString(6,request.getParameter("dob"));
				ps.setString(7,request.getParameter("age"));
				ps.setString(8,request.getParameter("country"));
				ps.setString(9,request.getParameter("address"));
				ps.setString(10,request.getParameter("city"));
				ps.setString(11,request.getParameter("state"));
				ps.setString(12,request.getParameter("pin"));
				ps.setString(13,request.getParameter("dlexp"));
				ps.setString(14,request.getParameter("dlcountry"));
				ps.setString(15,request.getParameter("aacm"));
				ps.setString(16,request.getParameter("usertype"));
				ps.setString(17,(String) session.getAttribute("username"));
				ps.executeUpdate();
				session.setAttribute("message", "Profile updated successfully");
				response.sendRedirect("/mavride/UserController?action=editupdatedProfile");
			} 
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	else
		doPost(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		User user = new User();
		UserErrorMsgs UerrorMsgs = new UserErrorMsgs();
		UpdateUser updateuser = new UpdateUser();
		UpdateUserErrorMsgs UUerrorMsgs = new UpdateUserErrorMsgs();

		if (action.equalsIgnoreCase("saveUser") ) {  
			getUserParam(request,user);
			user.validateUser(action,user,UerrorMsgs);
			session.setAttribute("user", user);
			if(!UerrorMsgs.getErrorMsg().equals("")) {
				getUserParam(request,user);
				session.setAttribute("errorMsgs", UerrorMsgs);
				response.sendRedirect("register.jsp");
			}
			else {
				UsersDAO.insertUser(user);
				response.sendRedirect("index.jsp");
			}
			}
		else if(action.equalsIgnoreCase("returnHome")) {
			String userrole = request.getParameter("userrole");
			if(userrole.equals("User")) {
				System.out.println(userrole);
				response.sendRedirect("userhome.jsp");
			}
			else if(userrole.equals("Manager")) {
				System.out.println(userrole);
				response.sendRedirect("managerhome.jsp");
			}
			else {
				System.out.println(userrole);
				response.sendRedirect("adminhome.jsp");
			}
		}
		else if(action.equalsIgnoreCase("updateUser")) {
			getUpdateParam(request,updateuser);
			updateuser.validateUpdateProfile(action,updateuser,UUerrorMsgs);
			session.setAttribute("user", updateuser);
			if(!UUerrorMsgs.getErrorMsg().equals("")) {
				getUpdateParam(request,updateuser);
				session.setAttribute("errorMsgs", UUerrorMsgs);
				response.sendRedirect("/mavride/UserController?action=listProfile");
			}
			else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavride?autoReconnect=true&useSSL=false", "root", "intel1");
					PreparedStatement ps = conn.prepareStatement("update users set prefix=?, firstname=?, lastname=?, password=?, phone=?, dob=?, age=?, country=?, address=?, city=?, state=?, pin=?, dlexp=?, dlcountry=?, aacm=?, usertype=? where username=?");
					ps.setString(1,request.getParameter("prefix"));
					ps.setString(2,request.getParameter("firstname"));
					ps.setString(3,request.getParameter("lastname"));
					ps.setString(4,request.getParameter("password"));
					ps.setString(5,request.getParameter("phone"));
					ps.setString(6,request.getParameter("dob"));
					ps.setString(7,request.getParameter("age"));
					ps.setString(8,request.getParameter("country"));
					ps.setString(9,request.getParameter("address"));
					ps.setString(10,request.getParameter("city"));
					ps.setString(11,request.getParameter("state"));
					ps.setString(12,request.getParameter("pin"));
					ps.setString(13,request.getParameter("dlexp"));
					ps.setString(14,request.getParameter("dlcountry"));
					ps.setString(15,request.getParameter("aacm"));
					ps.setString(16,request.getParameter("usertype"));
					ps.setString(17,(String) session.getAttribute("username"));
					ps.executeUpdate();
					session.setAttribute("message", "Profile updated successfully");
					response.sendRedirect("/mavride/UserController?action=updatedProfile");
					Cookie[] cookies = request.getCookies();
					int i = 0;
					for (Cookie cookie : cookies ) {

					  System.out.println(cookies[i].getName());
					  System.out.println(cookies[i].getValue());
					  session.setAttribute("username", cookies[1].getValue());
					  i++;
					}
				} 
				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
//		getServletContext().getRequestDispatcher(url).forward(request, response);
	}	

}
