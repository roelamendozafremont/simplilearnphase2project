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

@WebServlet("/selectpayments")
public class SelectAllPaymentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private static final String SELECT_SQL= "SELECT * FROM payments";
	
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
			out.println("<title>Booking Payments Table</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div align='left'>");
			out.println("<h3>Booking Payments Repository contains the following:</h3>");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>Bill Date</th>");
			out.println("<th>First Name</th>");			
			out.println("<th>Middle Name</th>");	
			out.println("<th>Last Name</th>");	
			out.println("<th>Card Number</th>");
			out.println("<th>Card Expiration</th>");
			out.println("<th>Street</th>");			
			out.println("<th>City</th>");	
			out.println("<th>State</th>");
			out.println("<th>Country</th>");
			out.println("<th>ZipCode</th>");
			out.println("<th>Bill Amount</th>");
			out.println("<th>Booking Number</th>");			
			out.println("</tr>");
			//step #4 traverse the ResultSet and obtain the values
			while(rs.next()) {
				
				out.println("<tr>");
				out.println("<td>"+ rs.getDate("bill_date")+"</td>");
				out.println("<td>"+rs.getString("bfirst_name")+"</td>");
				out.println("<td>"+rs.getString("bmiddle_name")+"</td>");
				out.println("<td>"+rs.getString("blast_name")+"</td>");
				out.println("<td>"+rs.getString("card_number")+"</td>");
				out.println("<td>"+rs.getString("card_expiration")+"</td>");
				out.println("<td>"+rs.getString("bstreet")+"</td>");		
				out.println("<td>"+rs.getString("bcity")+"</td>");
				out.println("<td>"+rs.getString("bstate")+"</td>");
				out.println("<td>"+rs.getString("bcountry")+"</td>");
				out.println("<td>"+rs.getString("bzipcode")+"</td>");
				out.println("<td>"+rs.getFloat("bill_amount")+"</td>");
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
