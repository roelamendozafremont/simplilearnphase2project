package com.simplilearn.workshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

@WebServlet("/ddloperations")
public class DDLOperationsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Connection connection = MySQLDatabaseUtils.getConnection();
			Statement statement = connection.createStatement();

			String CREATE_PAYMENTS_SQL = "create table payments ("
					+ "bill_date date,  bfirst_name char(20), bmiddle_name char(20), blast_name char(20), "
					+ "card_number char(20), card_expiration char(20),  "
					+ "bstreet char(20), bcity char(20), bstate char(20), bcountry char(20), "
					+ "bzipcode char(20), bill_amount float(6,2), book_number char(20) )";
			statement.execute(CREATE_PAYMENTS_SQL);
			
			String CREATE_CUSTOMERS_SQL = "create table customerBookings ("
					+ "flight_date date,  first_name char(20),middle_name char(20), last_name char(20), "
					+ "phone char(20), email char(20), origin char(20), destination char(20), age int, "
					+ "sex char(10), street char(20), city char(20), state char(20), country char(20), "
					+ "zipcode char(20), flight char(20), fare float(6,2), book_number char(20))";
			statement.execute(CREATE_CUSTOMERS_SQL);

			  String CREATE_ITERENARY_SQL =
			  "create table iterenaries ("
			  + "flight_date date, origin char(20),destination char(20), passenger_number int,"
			  + "flight_code char(20), fare float(7,2))";
			  statement.execute(CREATE_ITERENARY_SQL);
			  
			  String CREATE_PLACES_SQL =
			  "create table places ("
			  + "code char(20),street char(30), city char(20), state char(20),"
			  + "country char(20), zipcode char(20))";
			  statement.execute(CREATE_PLACES_SQL);
			  
			  String CREATE_AIRLINE_SQL =
			  "create table airlines ("
			  + "code char(20), name char(30), plane_type char(20))";
			  statement.execute(CREATE_AIRLINE_SQL);			  

			statement.close();
			connection.close();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<h3>Table Created </h3>");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
