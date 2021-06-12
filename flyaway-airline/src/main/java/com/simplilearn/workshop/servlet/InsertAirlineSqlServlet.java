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

@WebServlet("/insertairline")
public class InsertAirlineSqlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// Read the parameter's from request
			String code = request.getParameter("CODE");
			String name = request.getParameter("NAME");
			String plane = request.getParameter("PLANE");
			String seats = request.getParameter("SEATS");
			
			// Initialize the database
			Connection connection = MySQLDatabaseUtils.getConnection();

			String INSERT_SQL = "insert into airlines(code,name,plane_type, seat_capacity) "
					+ "values(?,?,?,?)";

			// create a Prepared Statement
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

			preparedStatement.setString(1, code.toUpperCase());
			preparedStatement.setString(2, name.toUpperCase());
			preparedStatement.setString(3, plane.toUpperCase());
			preparedStatement.setInt(4, Integer.parseInt(seats));
			preparedStatement.executeUpdate();

			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (preparedStatement.getUpdateCount() > 0) {
				out.println("<h3>Airline Successfully Inserted</h3>");
			} else {
				out.println("<h3>No Airline Record Inserted</h3>");
			}
			
			preparedStatement.close();
			connection.close();

		} catch (ClassNotFoundException | SQLException  e) {
			e.printStackTrace();
		}

	}

}
