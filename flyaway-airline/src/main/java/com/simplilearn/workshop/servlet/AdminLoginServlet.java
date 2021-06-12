package com.simplilearn.workshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Flyaway Airline Administration</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3> Welcome to simplilearn flyaway airline admin page </h3>");
		out.println("<div align='left'>");		
		out.println("<a href='http://localhost:8080/flyaway-airline/selectiterenaries'><button>Show All Iterenary Schedule</button></a>");
		out.println("<a href='insertiterenary.html'><button>Add Iterenary Schedule</button></a>");
		out.println("</br></br>");
		out.println("<a href='http://localhost:8080/flyaway-airline/selectplaces'><button>Show All Aiport Places</button></a>");		
		out.println("<a href='insertplace.html'><button>Add Airport Place</button></a>");		
		out.println("</br></br>");		
		out.println("<a href='http://localhost:8080/flyaway-airline/selectairlines'><button>Show All Airline Flight Schedules</button></a>");		
		out.println("<a href='insertairline.html'><button>Add Airline Flight Schedule</button></a>");	
		out.println("</br></br>");		
		out.println("<a href='http://localhost:8080/flyaway-airline/selectcustomers'><button>Show All Customer Bookings</button></a>");
		out.println("</br></br>");	
		out.println("<a href='http://localhost:8080/flyaway-airline/selectpayments'><button>Show All Booking Payments</button></a>");
		out.println("</br></br>");			
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
