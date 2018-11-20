package mavride.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import mavride.data.CarsDAO;
import mavride.data.RentalDAO;
import mavride.data.UsersDAO;
import mavride.model.Cars;
import mavride.model.Login;
import mavride.model.MyReservations;
import mavride.model.Rental;
import mavride.model.RentalErrorMsgs;
import mavride.model.Time;
import mavride.model.User;

@WebServlet("/ReserveRentalController")
public class ReserveRentalController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public void getRentalParam (HttpServletRequest request, Rental rental) {
		rental.setRental(request.getParameter("startdate"), request.getParameter("enddate"), request.getParameter("occupants"), request.getParameter("starttime"), request.getParameter("endtime"), request.getParameter("nameoncard"), request.getParameter("billingaddress"), request.getParameter("ccno"), request.getParameter("cvv"), request.getParameter("username"), request.getParameter("carname"), request.getParameter("capacity"), request.getParameter("weekdayrate"), request.getParameter("weekendrate"), request.getParameter("weekrate"), request.getParameter("gpsrate"), request.getParameter("onstarrate"), request.getParameter("siriusxmrate"));
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		Rental rental  = new Rental();
		RentalErrorMsgs RerrorMsgs = new RentalErrorMsgs();
		
		if (action.equalsIgnoreCase("listCars")) {
			getRentalParam(request, rental);
			rental.validateRental(action,rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
			}
			else {
			try {
				session.setAttribute("starttime", request.getParameter("starttime"));
				session.setAttribute("endtime", request.getParameter("endtime"));
				//list car and redirect to page
				System.out.println(request.getParameter("startdate"));
				ArrayList<Cars> carsInDB = new ArrayList<Cars>();
				int occupants = Integer.parseInt((String)session.getAttribute("occupants"));
				carsInDB=CarsDAO.listCars(occupants,(String)session.getAttribute("startdate")); 
				session.setAttribute("car", carsInDB);				
				getServletContext().getRequestDispatcher("/choosecar.jsp").forward(request, response);
			}
			catch(Exception e) {
				System.out.print(e.getMessage());
			}
			}
			}
		else if(action.equalsIgnoreCase("reserveRental")) {
			getRentalParam(request, rental);
			rental.validateRental(action,rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			session.setAttribute("occupants", request.getParameter("occupants"));
			Long dayCount = Rental.getDayCount(request.getParameter("startdate"), request.getParameter("enddate"));
			int daysCount = (int) (dayCount + 1);
			System.out.println("total days = "+daysCount);
			session.setAttribute("noofdays", daysCount);
			if(daysCount >= 7) {
				int weekCount = daysCount/7;
				int remainingDays = daysCount%7;
				System.out.println("weekcount = "+weekCount+" remaining days = "+remainingDays);
				session.setAttribute("noofweeks", weekCount);
				session.setAttribute("remainingdays", remainingDays);
			}else {
				int weekCount = 0;
				int remainingDays = 0;
				session.setAttribute("noofweeks", weekCount);
				session.setAttribute("remainingdays", remainingDays);
			}
			
			int noofweekends = Rental.getNumberOfWeekEnds(request.getParameter("startdate"), request.getParameter("enddate"));
			System.out.println("total weekend days = "+noofweekends);
			session.setAttribute("noofweekends", noofweekends);
			
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/reserverental.jsp").forward(request, response);
			}
			else {
				try {
					//check start date
					
					session.setAttribute("startdate", request.getParameter("startdate"));
					session.setAttribute("enddate", request.getParameter("enddate"));
					SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
					Date dt1=format1.parse(request.getParameter("startdate"));
					DateFormat format2=new SimpleDateFormat("EEEE"); 
					String finalDay=format2.format(dt1);
					//System.out.println(finalDay);
					//check end date
					//System.out.println(request.getParameter("enddate"));
					SimpleDateFormat format3=new SimpleDateFormat("yyyy-MM-dd");
					Date dt2=format3.parse(request.getParameter("enddate"));
					DateFormat format4=new SimpleDateFormat("EEEE"); 
					String enddate=format4.format(dt2);
					//System.out.println(enddate);
					if(finalDay.equalsIgnoreCase("saturday")){
						
						if(enddate.equalsIgnoreCase("saturday")) {
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeSaturday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						else if (enddate.equalsIgnoreCase("sunday")) {
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeSunday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						else{
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						
					}
					else if(finalDay.equalsIgnoreCase("sunday")) {
						if(enddate.equalsIgnoreCase("saturday")) {
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeSaturday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						else if (enddate.equalsIgnoreCase("sunday")) {
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeSunday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						else{
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
					}
					else{
						if(enddate.equalsIgnoreCase("saturday")) {
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeSaturday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						else if (enddate.equalsIgnoreCase("sunday")) {
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeSunday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						else{
							ArrayList<String> timeList = new ArrayList<String>();
							timeList=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList", timeList);
							ArrayList<String> timeList1 = new ArrayList<String>();
							timeList1=CarsDAO.listTimeWeekday(); 
							session.setAttribute("timeList1", timeList1);
							getServletContext().getRequestDispatcher("/reserverental1.jsp").forward(request, response);
						}
						
					}
				}
				catch(Exception e) {
					System.out.print(e.getMessage());
				}
			}
		}
		else if(action.equalsIgnoreCase("selectExtras")) {
			session.setAttribute("selectedCar", request.getParameter("selectedCar"));
			
			String gps = CarsDAO.listGPS(request.getParameter("selectedCar")); 
			session.setAttribute("gps", gps);
			
			String onstar = CarsDAO.listOnStar(request.getParameter("selectedCar"));
			session.setAttribute("onstar", onstar);
			
			String siriusxm = CarsDAO.listSiriusXM(request.getParameter("selectedCar")); 
			session.setAttribute("siriusxm", siriusxm);
			
			getServletContext().getRequestDispatcher("/addextras.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("rentalSummary")) {
			session.setAttribute("gps", request.getParameter("gps"));
			session.setAttribute("onstar", request.getParameter("onstar"));
			session.setAttribute("siriusxm", request.getParameter("siriusxm"));
			
			ArrayList<String> priceinDB = new ArrayList<String>();
			priceinDB=CarsDAO.getPrice((String)session.getAttribute("selectedCar")); 
			session.setAttribute("price", priceinDB);
			System.out.println(Float.parseFloat(priceinDB.get(0).toString()));
			int noofweeks = (Integer)session.getAttribute("noofweeks");
			int noofdays = (Integer)session.getAttribute("noofdays");
			int remainingdays = (Integer)session.getAttribute("remainingdays");
			int noofweekends = (Integer)session.getAttribute("noofweekends");
			int age1 = (Integer)session.getAttribute("age");
			int noofweekdays = noofdays - noofweekends;
			int gpsrate,onstarrate,siriusxmrate;
			float discount;
			float depositamt;
			String aacmstatus = (String) session.getAttribute("aacm");
			System.out.println("aacm status = "+aacmstatus);
			if(request.getParameter("gps") == null) {
				gpsrate = 0;
			}
			else {
				gpsrate = Integer.parseInt(request.getParameter("gps"));
			}
			if(request.getParameter("onstar") == null) {
				onstarrate = 0;
			}
			else {
				onstarrate = Integer.parseInt(request.getParameter("onstar"));
			}
			if(request.getParameter("siriusxm") == null) {
				siriusxmrate = 0;
			}
			else {
				siriusxmrate = Integer.parseInt(request.getParameter("siriusxm"));
			}
			if(noofweeks==0) {
				
				if(age1 < 25) {
					depositamt = (float)250.00;
				}
				else {
					depositamt = (float)0.00;
				}
				
				float price1 = noofweekdays * Float.parseFloat(priceinDB.get(0).toString());
				float price2 = noofweekends * Float.parseFloat(priceinDB.get(1).toString());
				
				float price3 = noofweekdays * gpsrate;
				float price4 = noofweekdays * onstarrate;
				float price5 = noofweekdays * siriusxmrate;
				
				float price6 = noofweekends * gpsrate;
				float price7 = noofweekends * onstarrate;
				float price8 = noofweekends * siriusxmrate;
				float baseprice = price1 + price2;
				float extrasprice = price3+ price4+ price5+ price6+ price7+ price8;
				float totalprice = price1 + price2+ price3+ price4+ price5+ price6+ price7+ price8+ depositamt;
				
				float taxapplied = (float) (totalprice * 0.08);
								
				if(aacmstatus.equalsIgnoreCase("yes")) {
					discount = (float) (totalprice * 0.1);
				}else {
					discount = (float) 0.00;
				}
				
				float totalpricewithtax = taxapplied + totalprice - discount;
				session.setAttribute("discount", discount);
				session.setAttribute("tax", taxapplied);
				session.setAttribute("baseprice", baseprice);
				session.setAttribute("extrasprice", extrasprice);
				session.setAttribute("depositamt", depositamt);
				session.setAttribute("totalprice", totalpricewithtax);
				System.out.println("total price is  = "+totalprice);
			}
			else {
				
				if(age1 < 25) {
					depositamt = (float)250.00;
				}
				else {
					depositamt = (float)0.00;
				}
				
				float price9 = noofweeks * Float.parseFloat(priceinDB.get(2).toString());
				float price10 = remainingdays * Float.parseFloat(priceinDB.get(0).toString());
				
				float price11 = noofweeks * 7 * gpsrate;
				float price12 = noofweeks * 7 * onstarrate;
				float price13 = noofweeks * 7 * siriusxmrate;
				
				float price14 = remainingdays * gpsrate;
				float price15 = remainingdays * onstarrate;
				float price16 = remainingdays * siriusxmrate;
				float baseprice = price9 + price10;
				float extrasprice = price11+ price12+ price13+ price14+ price15+ price16;
				float totalprice = price9 + price10+ price11+ price12+ price13+ price14+ price15+ price16+ depositamt;
				float taxapplied = (float) (totalprice * 0.08);
				if(aacmstatus.equalsIgnoreCase("yes")) {
					discount = (float) (totalprice * 0.1);
				}else {
					discount = (float) 0.00;
				}
				float totalpricewithtax = taxapplied + totalprice - discount;
				session.setAttribute("discount", discount);
				session.setAttribute("tax", taxapplied);
				session.setAttribute("baseprice", baseprice);
				session.setAttribute("extrasprice", extrasprice);
				session.setAttribute("depositamt", depositamt);
				session.setAttribute("totalprice", totalpricewithtax);
				System.out.println("total price is  = "+totalprice);
			}
			getServletContext().getRequestDispatcher("/rentalsummary.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("makePayment")) {
			getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("confirmRental")) {
			getRentalParam(request, rental);
			rental.validateRental(action,rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/payment.jsp").forward(request, response);
			}
			else {
				String username = (String) session.getAttribute("username");
				String name = (String) session.getAttribute("firstname");
				String carname = (String) session.getAttribute("selectedCar");
				float baseprice = (Float)session.getAttribute("baseprice");
				float taxapplied = (Float) session.getAttribute("tax");
				float discount = (Float) session.getAttribute("discount");
				String startdate = (String) session.getAttribute("startdate");
				String starttime = (String) session.getAttribute("starttime");
				String enddate = (String) session.getAttribute("enddate");
				String endtime = (String) session.getAttribute("endtime");
				float totalamount = (Float) session.getAttribute("totalprice");
				String nameoncard = request.getParameter("nameoncard");
				String billingaddress = request.getParameter("billingaddress");
				String ccno = request.getParameter("ccno");
				String cctype = request.getParameter("cctype");
				String expdate = request.getParameter("ccmonth")+" "+request.getParameter("ccyear");
				String cvvno = request.getParameter("cvv");
				session.setAttribute("message", "Rental Confirmed!");
				RentalDAO.StoreRentalInDB(username, name, carname, baseprice, taxapplied, discount, startdate, starttime, enddate, endtime, totalamount, nameoncard, billingaddress, ccno, cctype, expdate, cvvno);
				ArrayList<String> rentalsummary = new ArrayList<String>();
				rentalsummary=RentalDAO.GetReservationSummary(); 
				session.setAttribute("rentalsummary", rentalsummary);
				System.out.println(rentalsummary);
				getServletContext().getRequestDispatcher("/rentalconfirmation.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("listMyReservations")) {
			String userrole = (String)session.getAttribute("userrole");
			if(userrole.equalsIgnoreCase("manager")) {
				ArrayList<MyReservations> myrentals = new ArrayList<MyReservations>();
				myrentals=RentalDAO.getAllReservations();
				session.setAttribute("myrentals", myrentals);	
				getServletContext().getRequestDispatcher("/myreservations.jsp").forward(request, response);
			}
			else {
				ArrayList<MyReservations> myrentals = new ArrayList<MyReservations>();
				myrentals=RentalDAO.getMyReservations((String)session.getAttribute("username"));
				session.setAttribute("myrentals", myrentals);	
				getServletContext().getRequestDispatcher("/myreservations.jsp").forward(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("viewRental")) {
			if(request.getParameter("myaction").equalsIgnoreCase("viewrental")) {
				int rentalid = Integer.parseInt(request.getParameter("rentalid"));
				ArrayList<String> rentaldetail = new ArrayList<String>();
				rentaldetail=RentalDAO.GetReservationDetails(rentalid);
				session.setAttribute("rentalsummary", rentaldetail);
				getServletContext().getRequestDispatcher("/rentalconfirmation.jsp").forward(request, response);
			}
			else {
				int rentalid = Integer.parseInt(request.getParameter("rentalid"));
				RentalDAO.deleteRental(rentalid);
				session.setAttribute("message", "Reservation deleted successfully!");
				getServletContext().getRequestDispatcher("/ReserveRentalController?action=listMyReservations").forward(request, response);
			}
			
		}
		else if(action.equalsIgnoreCase("returnHome")) {
			String userrole = (String)session.getAttribute("userrole");
			if(userrole.equalsIgnoreCase("user")) {
				getServletContext().getRequestDispatcher("/userhome.jsp").forward(request, response);
			}
			else if(userrole.equalsIgnoreCase("manager")) {
				getServletContext().getRequestDispatcher("/managerhome.jsp").forward(request, response);
			}
			else {
				getServletContext().getRequestDispatcher("/adminhome.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("addRental")) {
			getRentalParam(request, rental);
			rental.validateRental(action,rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/addrental.jsp").forward(request, response);
			}
			else {
				session.setAttribute("username", request.getParameter("username"));
				session.setAttribute("firstname", request.getParameter("username"));
				getServletContext().getRequestDispatcher("/reserverental.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("viewBookings")) {
			getServletContext().getRequestDispatcher("/viewbooking.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("viewBookingsonDate")) {
			String date = request.getParameter("sdate");
			ArrayList<MyReservations> myrentals = new ArrayList<MyReservations>();
			myrentals=RentalDAO.getAllReservationsonDate(date);
			session.setAttribute("myrentals", myrentals);
			getServletContext().getRequestDispatcher("/myreservations.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("viewCars")) {
			getServletContext().getRequestDispatcher("/viewavailablecars.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("viewavailablecars")) {
			getRentalParam(request, rental);
			rental.validateRental("reserveRental",rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/viewavailablecars.jsp").forward(request, response);
			}
			else {
				session.setAttribute("occupants", request.getParameter("occupants"));
				Long dayCount = Rental.getDayCount(request.getParameter("startdate"), request.getParameter("enddate"));
				int daysCount = (int) (dayCount + 1);
				System.out.println("total days = "+daysCount);
				session.setAttribute("noofdays", daysCount);
				if(daysCount >= 7) {
					int weekCount = daysCount/7;
					int remainingDays = daysCount%7;
					System.out.println("weekcount = "+weekCount+" remaining days = "+remainingDays);
					session.setAttribute("noofweeks", weekCount);
					session.setAttribute("remainingdays", remainingDays);
				}else {
					int weekCount = 0;
					int remainingDays = 0;
					session.setAttribute("noofweeks", weekCount);
					session.setAttribute("remainingdays", remainingDays);
				}
				
				int noofweekends = Rental.getNumberOfWeekEnds(request.getParameter("startdate"), request.getParameter("enddate"));
				System.out.println("total weekend days = "+noofweekends);
				session.setAttribute("noofweekends", noofweekends);
				
				if(!RerrorMsgs.getErrorMsg().equals("")) {
					System.out.println("inside get error = blank");
					getRentalParam(request, rental);
					session.setAttribute("rentalErrorMsgs", RerrorMsgs);
					getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
				}
				else {
					try {
						//check start date
						
						session.setAttribute("startdate", request.getParameter("startdate"));
						session.setAttribute("enddate", request.getParameter("enddate"));
						SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
						Date dt1=format1.parse(request.getParameter("startdate"));
						DateFormat format2=new SimpleDateFormat("EEEE"); 
						String finalDay=format2.format(dt1);
						//System.out.println(finalDay);
						//check end date
						//System.out.println(request.getParameter("enddate"));
						SimpleDateFormat format3=new SimpleDateFormat("yyyy-MM-dd");
						Date dt2=format3.parse(request.getParameter("enddate"));
						DateFormat format4=new SimpleDateFormat("EEEE"); 
						String enddate=format4.format(dt2);
						//System.out.println(enddate);
						if(finalDay.equalsIgnoreCase("saturday")){
							
							if(enddate.equalsIgnoreCase("saturday")) {
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeSaturday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							else if (enddate.equalsIgnoreCase("sunday")) {
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeSunday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							else{
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							
						}
						else if(finalDay.equalsIgnoreCase("sunday")) {
							if(enddate.equalsIgnoreCase("saturday")) {
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeSaturday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							else if (enddate.equalsIgnoreCase("sunday")) {
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeSunday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							else{
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
						}
						else{
							if(enddate.equalsIgnoreCase("saturday")) {
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeSaturday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							else if (enddate.equalsIgnoreCase("sunday")) {
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeSunday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							else{
								ArrayList<String> timeList = new ArrayList<String>();
								timeList=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList", timeList);
								ArrayList<String> timeList1 = new ArrayList<String>();
								timeList1=CarsDAO.listTimeWeekday(); 
								session.setAttribute("timeList1", timeList1);
								getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
							}
							
						}
					}
					catch(Exception e) {
						System.out.print(e.getMessage());
					}
				}
			}
			
		}
		else if(action.equalsIgnoreCase("listavailableCars")) {
			getRentalParam(request, rental);
			rental.validateRental("listCars",rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/viewavailablecars1.jsp").forward(request, response);
			}
			else {
				ArrayList<Cars> carsInDB = new ArrayList<Cars>();
				int occupants = Integer.parseInt((String)session.getAttribute("occupants"));
				carsInDB=CarsDAO.listCars(occupants,(String)session.getAttribute("startdate")); 
				session.setAttribute("car", carsInDB);
				getServletContext().getRequestDispatcher("/viewavailablecars2.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("addCar")) {
			getServletContext().getRequestDispatcher("/addcar.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("addCar1")) {
			getRentalParam(request, rental);
			rental.validateRental(action,rental,RerrorMsgs);
			session.setAttribute("rental", rental);
			if(!RerrorMsgs.getErrorMsg().equals("")) {
				System.out.println("inside get error = blank");
				getRentalParam(request, rental);
				session.setAttribute("rentalErrorMsgs", RerrorMsgs);
				getServletContext().getRequestDispatcher("/addcar.jsp").forward(request, response);
			}
			else {
				CarsDAO.addCar(request.getParameter("carname"),request.getParameter("capacity"),request.getParameter("weekdayrate"),request.getParameter("weekendrate"),request.getParameter("weekrate"),request.getParameter("gpsrate"),request.getParameter("onstarrate"),request.getParameter("siriusxmrate"));
				ArrayList<Cars> carsInDB = new ArrayList<Cars>();
				carsInDB=CarsDAO.listAllCars(); 
				session.setAttribute("car", carsInDB);
				getServletContext().getRequestDispatcher("/listallcars.jsp").forward(request, response);
			}
		}
		else if(action.equalsIgnoreCase("deleteCar")) {
			CarsDAO.deleteCar(request.getParameter("selectedCar"));
			session.setAttribute("message", "Car deleted successfully!");
			ArrayList<Cars> carsInDB = new ArrayList<Cars>();
			carsInDB=CarsDAO.listAllCars(); 
			session.setAttribute("car", carsInDB);
			getServletContext().getRequestDispatcher("/listallcars.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("delCar")) {
			ArrayList<Cars> carsInDB = new ArrayList<Cars>();
			carsInDB=CarsDAO.listAllCars(); 
			session.setAttribute("car", carsInDB);
			getServletContext().getRequestDispatcher("/listallcars.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
	}
}
