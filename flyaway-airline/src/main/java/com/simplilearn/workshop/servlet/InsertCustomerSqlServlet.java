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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

@WebServlet("/insertcustomer")
public class InsertCustomerSqlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Read the parameter's from request
			String firstname = request.getParameter("FNAME");
			String middlename = request.getParameter("MNAME");
			String lastname = request.getParameter("LNAME");
			String phone = request.getParameter("PHONE");
			String email = request.getParameter("EMAIL");

			Cookie[] cookiesData = request.getCookies();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf.parse(cookiesData[0].getValue());
			java.sql.Date date2 = new java.sql.Date(date1.getTime());
			
			String origin = cookiesData[1].getValue();
			String destination = cookiesData[2].getValue();
			String flight = cookiesData[3].getValue();
			
			String fareStr = cookiesData[4].getValue();
			Float fare = Float.parseFloat(fareStr);			
			
			Integer age = Integer.parseInt(request.getParameter("AGE"));
			String sex = request.getParameter("SEX");
			String street = request.getParameter("STREET");
			String city = request.getParameter("CITY");
			String state = request.getParameter("STATE");
			String country = request.getParameter("COUNTRY");
			String zipcode = request.getParameter("ZIPCODE");
			String booking = generateBookingNumber();
			
			Cookie fnameData = new Cookie("firstname", firstname);
			response.addCookie(fnameData);
			Cookie mnameData = new Cookie("middlename", middlename);
			response.addCookie(mnameData);
			Cookie lnameData = new Cookie("lastname", lastname);
			response.addCookie(lnameData);
			Cookie bkgNumData = new Cookie("bookingnumber", booking);
			response.addCookie(bkgNumData);
			
			// Initialize the database
			Connection connection = MySQLDatabaseUtils.getConnection();

			String INSERT_SQL = "insert into customerBookings(flight_date,first_name, middle_name, last_name, phone,"
					+ " email, origin,destination,age, sex, street, city, state, country, zipcode, flight, fare, book_number) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// create a Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

			preparedStatement.setDate(1, date2);
			preparedStatement.setString(2, firstname.toUpperCase());
			preparedStatement.setString(3, middlename.toUpperCase());
			preparedStatement.setString(4, lastname.toUpperCase());
			preparedStatement.setString(5, phone.toUpperCase());
			preparedStatement.setString(6, email.toUpperCase());
			preparedStatement.setString(7, origin.toUpperCase());
			preparedStatement.setString(8, destination.toUpperCase());
			preparedStatement.setInt(9, age);
			preparedStatement.setString(10, sex.toUpperCase());
			preparedStatement.setString(11, street.toUpperCase());
			preparedStatement.setString(12, city.toUpperCase());
			preparedStatement.setString(13, state.toUpperCase());
			preparedStatement.setString(14, country.toUpperCase());
			preparedStatement.setString(15, zipcode.toUpperCase());
			preparedStatement.setString(16, flight.toUpperCase());
			preparedStatement.setFloat(17, fare);
			preparedStatement.setString(18, booking);

			preparedStatement.executeUpdate();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (preparedStatement.getUpdateCount() > 0) {
				out.println("<h3>Customer " + firstname + " " + middlename + " " + lastname
						+ " Successfully Inserted</h3>");
			} else {
				out.println("<h3>No Customer Record Inserted</h3>");
			}
			preparedStatement.close();
			connection.close();
			out.println("<a href='insertcustomer.html'><button>Book Another</button></a>");
			out.println("<a href='paybookingnow.html'><button>Pay Now</button></a>");

		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}

	}

	public static String generateBookingNumber() {

		int length = 8;
		String digits = "0123456789";
		String all = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + digits ;
		Random rnd = new Random();
		List<String> result = new ArrayList<>();
		Consumer<String> appendChar = s -> result.add("" + s.charAt(rnd.nextInt(s.length())));
		appendChar.accept(digits);
		while (result.size() < length)
			appendChar.accept(all);
		Collections.shuffle(result, rnd);
		String str = String.join("", result);
		return str;

	}

}
