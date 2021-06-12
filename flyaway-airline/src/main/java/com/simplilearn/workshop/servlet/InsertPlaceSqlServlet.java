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

@WebServlet("/insertplace")
public class InsertPlaceSqlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Read the parameter's from request
			String code = request.getParameter("CODE");
			String street = request.getParameter("STREET");
			String city = request.getParameter("CITY");
			String state = request.getParameter("STATE");
			String country = request.getParameter("COUNTRY");
			String zipcode = request.getParameter("ZIPCODE");
			
			// Initialize the database
			Connection connection = MySQLDatabaseUtils.getConnection();

			String INSERT_SQL = "insert into places(code,street,city,state,country,zipcode) "
					+ "values(?,?,?,?,?,?)";

			// create a Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

			preparedStatement.setString(1, code.toUpperCase());
			preparedStatement.setString(2, street.toUpperCase());
			preparedStatement.setString(3, city.toUpperCase());
			preparedStatement.setString(4, state.toUpperCase());
			preparedStatement.setString(5, country.toUpperCase());
			preparedStatement.setString(6, zipcode.toUpperCase());			
			preparedStatement.executeUpdate();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (preparedStatement.getUpdateCount() > 0) {
				out.println("<h3>Place Successfully Inserted</h3>");
			} else {
				out.println("<h3>No Place Record Inserted</h3>");
			}
			
			preparedStatement.close();
			connection.close();

		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}

	}

}
