package com.simplilearn.workshop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

@WebServlet("/searchiterenary")
public class SearchIterenaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {		
		
		try {
			
			String datestr = request.getParameter("DATE");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date date1 = sdf.parse(datestr);
			java.sql.Date date2 = new java.sql.Date(date1.getTime());
			String origin = request.getParameter("ORIGIN");
			String destination = request.getParameter("DESTINATION");
			String seatstr = request.getParameter("SEATS");

			Cookie flightTimeData = new Cookie("flightTime", date2.toString());
			response.addCookie(flightTimeData);
			Cookie originData = new Cookie("origin", origin);
			response.addCookie(originData);
			Cookie destinationData = new Cookie("destination", destination);
			response.addCookie(destinationData);
			
			String SELECT_SQL= "SELECT * FROM iterenaries where flight_date = '" + date2 
					+ "' and origin = '" + origin + "' and destination = '" + destination + "' and seats_available >= " + seatstr;
			
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
			out.println("<title>Iterenaries Table</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>simplilearn available flights:</h3>");
			out.println("<div align='left'>");
			out.println("<table border=1>");
			out.println("<tr>");
			out.println("<th>Flight Date</th>");
			out.println("<th>Origin Airport</th>");
			out.println("<th>Destination Airport</th>");
			out.println("<th>Seats Available</th>");
			out.println("<th>Flight Code</th>");
			out.println("<th>Fare Price</th>");			
			out.println("</tr>");
			//step #4 traverse the ResultSet and obtain the values
			while(rs.next()) {
				
				String flightCode = rs.getString("flight_code");
				Float farePrice = rs.getFloat("fare");
				
				out.println("<tr>");
				out.println("<td>"+ rs.getDate("flight_date")+"</td>");
				out.println("<td>"+rs.getString("origin")+"</td>");
				out.println("<td>"+rs.getString("destination")+"</td>");
				out.println("<td>"+rs.getInt("seats_available")+"</td>");
				out.println("<td>"+flightCode+"</td>");
				out.println("<td>"+farePrice+"</td>");				
				out.println("</tr>");
				
				Cookie flightdata = new Cookie("flight", flightCode);
				response.addCookie(flightdata);
				Cookie fareData = new Cookie("fare", farePrice.toString());
				response.addCookie(fareData);
				
			}
			
			rs.close();
			statement.close();
			connection.close();
			out.println("</table>");
			out.println("</div>");
			out.println("</br>");
			out.println("<div align='left'>");		
			out.println("<a href='insertcustomer.html'><button>Book Now</button></a>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");			
			
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			
			e.printStackTrace();
		}
		
	}
}
