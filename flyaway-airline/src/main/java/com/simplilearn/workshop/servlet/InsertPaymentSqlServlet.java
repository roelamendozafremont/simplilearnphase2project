package com.simplilearn.workshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

import java.sql.*;


@WebServlet("/insertpayment")
public class InsertPaymentSqlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Read the parameter's from request
			String firstname = request.getParameter("BFNAME");
			String middlename = request.getParameter("BMNAME");
			String lastname = request.getParameter("BLNAME");
			String cardnum = request.getParameter("BCARDNUM");
			String cardexp = request.getParameter("BCARDEXP");

			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			Cookie[] cookiesData = request.getCookies();

			String fareStr = cookiesData[4].getValue();
			Float fare = Float.parseFloat(fareStr);
			String booking = cookiesData[8].getValue();

			String bstreet = request.getParameter("BSTREET");
			String bcity = request.getParameter("BCITY");
			String bstate = request.getParameter("BSTATE");
			String bcountry = request.getParameter("BCOUNTRY");
			String bzipcode = request.getParameter("BZIPCODE");

			// Initialize the database
			Connection connection = MySQLDatabaseUtils.getConnection();

			String INSERT_SQL = "insert into payments(bill_date,bfirst_name, bmiddle_name, blast_name, card_number,"
					+ " card_expiration, bstreet, bcity, bstate, bcountry, bzipcode, bill_amount, book_number) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// create a Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

			preparedStatement.setDate(1, date);
			preparedStatement.setString(2, firstname.toUpperCase());
			preparedStatement.setString(3, middlename.toUpperCase());
			preparedStatement.setString(4, lastname.toUpperCase());
			preparedStatement.setString(5, cardnum.toUpperCase());
			preparedStatement.setString(6, cardexp.toUpperCase());
			preparedStatement.setString(7, bstreet.toUpperCase());
			preparedStatement.setString(8, bcity.toUpperCase());
			preparedStatement.setString(9, bstate.toUpperCase());
			preparedStatement.setString(10, bcountry.toUpperCase());
			preparedStatement.setString(11, bzipcode.toUpperCase());
			preparedStatement.setFloat(12, fare);
			preparedStatement.setString(13, booking);

			preparedStatement.executeUpdate();
			
			if (preparedStatement.getUpdateCount() > 0) {
				showCustomerBooking(request, response, cardnum);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<h3>No Payment Record Inserted</h3>");
			}
			preparedStatement.close();
			connection.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public static void showCustomerBooking(HttpServletRequest request, HttpServletResponse response, String cardNumber)
			throws ServletException, IOException {

		try {

			Cookie[] cookiesData = request.getCookies();

			String date = cookiesData[0].getValue();
			String origin = cookiesData[1].getValue();
			String destination = cookiesData[2].getValue();
			String flight = cookiesData[3].getValue();
			String fareStr = cookiesData[4].getValue();
			String fname = cookiesData[5].getValue();
			String mname = cookiesData[6].getValue();
			String lname = cookiesData[7].getValue();
			String booking = cookiesData[8].getValue();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println("<title>Customer Booking Ticket</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div align='left'>");
			out.println("<h3>Card Payment " + cardNumber + " Successfully Charged</h3>");
			out.println("<h3>See Booking Details below:</h3>");			
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<td>Flight Date  : </td>");
			out.println("<td>" + date + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Origin Airport  : </td>");
			out.println("<td>" + origin.toUpperCase() + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Destination Airport  : </td>");
			out.println("<td>" + destination.toUpperCase() + "</td>");
			out.println("</tr>");		
			out.println("<tr>");
			out.println("<td>Flight Code  : </td>");
			out.println("<td>" + flight + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Fare Price  : </td>");
			out.println("<td>" + fareStr + "</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Booking Number  : </td>");
			out.println("<td>" + booking + "</td>");
			out.println("</tr>");		
			out.println("<tr>");
			out.println("<td>First Name  : </td>");
			out.println("<td>" + fname.toUpperCase() + "</td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td>Middle Name  : </td>");
			out.println("<td>" + mname.toUpperCase() + "'</td>");
			out.println("</tr>");	
			out.println("<tr>");
			out.println("<td>Last Name  : </td>");
			out.println("<td>" + lname.toUpperCase() + "</td>");
			out.println("</tr>");				
			out.println("</table>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
