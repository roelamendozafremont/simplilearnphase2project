package com.simplilearn.workshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/insertiterenary")
public class InsertIterenarySqlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Read the parameter's from request
			String datestr = request.getParameter("DATE");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf.parse(datestr);
			String origin = request.getParameter("ORIGIN");
			String destination = request.getParameter("DESTINATION");
			Integer seats = Integer.parseInt(request.getParameter("SEATS"));
			String flight = request.getParameter("FLIGHT");
			String fare = request.getParameter("FARE");
			
			// Initialize the database
			Connection connection = MySQLDatabaseUtils.getConnection();

			String INSERT_SQL = "insert into iterenaries(flight_date,origin,destination,seats_available,flight_code, fare) "
					+ "values(?,?,?,?,?,?)";

			// create a Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

			preparedStatement.setDate(1, new java.sql.Date(date1.getTime()));
			preparedStatement.setString(2, origin.toUpperCase());
			preparedStatement.setString(3, destination.toUpperCase());
			preparedStatement.setInt(4, seats);
			preparedStatement.setString(5, flight.toUpperCase());
			preparedStatement.setFloat(6, Float.parseFloat(fare));
			preparedStatement.executeUpdate();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (preparedStatement.getUpdateCount() > 0) {
				out.println("<h3>Iterenary Successfully Inserted</h3>");
			} else {
				out.println("<h3>No Iterenary Record Inserted</h3>");
			}
			
			preparedStatement.close();
			connection.close();

		} catch (ClassNotFoundException | SQLException | ParseException e) {
			e.printStackTrace();
		}

	}

}
