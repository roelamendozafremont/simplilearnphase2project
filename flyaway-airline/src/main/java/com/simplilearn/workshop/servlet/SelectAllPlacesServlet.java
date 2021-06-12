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

@WebServlet("/selectplaces")
public class SelectAllPlacesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private static final String SELECT_SQL= "SELECT * FROM places";
	
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
			out.println("<title>Places Table</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div align='left'>");
			out.println("<h3>Airport Repository contains the following:</h3>");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>Airport Code</th>");
			out.println("<th>Street</th>");
			out.println("<th>City</th>");
			out.println("<th>State</th>");
			out.println("<th>Country</th>");
			out.println("<th>ZipCode</th>");			
			out.println("</tr>");
			//step #4 traverse the ResultSet and obtain the values
			while(rs.next()) {
				
				out.println("<tr>");
				out.println("<td>"+ rs.getString("code")+"</td>");
				out.println("<td>"+rs.getString("street")+"</td>");
				out.println("<td>"+rs.getString("city")+"</td>");
				out.println("<td>"+ rs.getString("state")+"</td>");
				out.println("<td>"+rs.getString("country")+"</td>");
				out.println("<td>"+rs.getString("zipcode")+"</td>");				
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
