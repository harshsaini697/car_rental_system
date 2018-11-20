package mavride.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mavride.model.Login;
import mavride.data.UsersDAO;
import mavride.model.*;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void getLoginParam (HttpServletRequest request, Login login) {
		login.setLogin(request.getParameter("username"), request.getParameter("password"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		Login login = new Login();
		LoginErrorMsgs LerrorMsgs = new LoginErrorMsgs();
		
		if(action.equalsIgnoreCase("loginUser")) {
			getLoginParam(request,login);
			login.validateLogin(action,login,LerrorMsgs);
			session.setAttribute("login", login);
			if(!LerrorMsgs.getErrorMsg().equals("")) {
				getLoginParam(request,login);
				session.setAttribute("errorMsgs", LerrorMsgs);
				url = "/index.jsp";
			}
			else{
				try {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mavride?autoReconnect=true&useSSL=false", "root", "intel1");
					PreparedStatement ps = conn.prepareStatement("select username,password,userrole,firstname,aacm,status,age from users where username=? and password=?");
					ps.setString(1, username);
					ps.setString(2, password);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						session.setAttribute("firstname", rs.getString(4));
						session.setAttribute("username", rs.getString(1));
						session.setAttribute("userrole", rs.getString(3));
						session.setAttribute("aacm", rs.getString(5));
						session.setAttribute("age", rs.getInt(7));
						Cookie loginCookie = new Cookie("user",rs.getString(1));
						loginCookie.setMaxAge(30*60);
						response.addCookie(loginCookie);
						String ur = rs.getString(3);
						if(rs.getString(6).equalsIgnoreCase("Revoked")) {
							response.sendRedirect("loginrevoked.jsp");
						}
						else if(ur.equalsIgnoreCase("User")) {
							response.sendRedirect("userhome.jsp");
						}
						else if(ur.equalsIgnoreCase("Manager")) {
							response.sendRedirect("managerhome.jsp");
						}
						else{
							response.sendRedirect("adminhome.jsp");
						}
						return;
					}
				response.sendRedirect("loginerror.jsp");
				return;
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}
