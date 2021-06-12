package com.simplilearn.workshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

@WebServlet("/selectcustomers")
public class SelectAllCustomersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private static final String SELECT_SQL= "SELECT * FROM customerbookings";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			ResultSet rs = statement.executeQuery(SELECT_SQL);
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Customer Bookings Table</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div align='left'>");
			out.println("<h3>Customer Booking Repository contains the following:</h3>");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>Flight Date</th>");
			out.println("<th>First Name</th>");			
			out.println("<th>Middle Name</th>");	
			out.println("<th>Last Name</th>");	
			out.println("<th>Phone Number</th>");
			out.println("<th>Email Address</th>");
			out.println("<th>Origin Airport</th>");
			out.println("<th>Destination Airport</th>");
			out.println("<th>Age</th>");
			out.println("<th>Sex</th>");
			out.println("<th>Street</th>");			
			out.println("<th>City</th>");	
			out.println("<th>State</th>");
			out.println("<th>Country</th>");
			out.println("<th>ZipCode</th>");
			out.println("<th>Flight Code</th>");
			out.println("<th>Price Fare</th>");
			out.println("<th>Booking Number</th>");			
			out.println("</tr>");
			//step #4 traverse the ResultSet and obtain the values
			while(rs.next()) {
				
				out.println("<tr>");
				out.println("<td>"+ rs.getDate("flight_date")+"</td>");
				out.println("<td>"+rs.getString("first_name")+"</td>");
				out.println("<td>"+rs.getString("middle_name")+"</td>");
				out.println("<td>"+rs.getString("last_name")+"</td>");
				out.println("<td>"+rs.getString("phone")+"</td>");
				out.println("<td>"+rs.getString("email")+"</td>");
				out.println("<td>"+rs.getString("origin")+"</td>");
				out.println("<td>"+rs.getString("destination")+"</td>");
				out.println("<td>"+rs.getInt("age")+"</td>");
				out.println("<td>"+rs.getString("sex")+"</td>");
				out.println("<td>"+rs.getString("street")+"</td>");		
				out.println("<td>"+rs.getString("city")+"</td>");
				out.println("<td>"+rs.getString("state")+"</td>");
				out.println("<td>"+rs.getString("country")+"</td>");
				out.println("<td>"+rs.getString("zipcode")+"</td>");
				out.println("<td>"+rs.getString("flight")+"</td>");
				out.println("<td>"+rs.getFloat("fare")+"</td>");
				out.println("<td>"+rs.getString("book_number")+"</td>");				
				out.println("</tr>");
				
			}
			
			rs.close();
			statement.close();
			connection.close();
			out.println("</table>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
