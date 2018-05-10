
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.DBConnection;
import helper.MovieListing;

// this annotation maps this Java Servlet Class to a URL
@WebServlet("/StarServlet")
public class StarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StarServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
       
        String starId = request.getParameter("id");
        ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
        // set response mime type
        response.setContentType("text/html"); 

        // get the printwriter for writing response
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
      
        
        

        
        out.println("<head>");
        
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");

        
        out.println("<title>Single Star</title></head>");
        
        out.println("<%@include file=\"navbar.jsp\" %>");

        try {
        		
        		// create database connection
        		Connection connection = DBConnection.getConnection();
        		
        		// prepare query
        		String query = "SELECT * from stars where id=?";
        		PreparedStatement pst=connection.prepareStatement(query);
        		pst.setString(1, starId);
        		// execute query
        		ResultSet rst=pst.executeQuery();
        		
        		out.println("<center>");

        		out.println("<body>");
        		out.println("<div class=\"container\">");

        		out.println("<h1>Details of Star</h1>");
        		out.println("<table>");
        		
        		// add table header row
        		out.println("<tr style=\"font-size: large; font-weight: bold\">");
        		out.println("<td>Movie List</td>");
        		out.println("<td>Name</td>");
        		out.println("<td>Birth Year</td>");
        		out.println("</tr>");
        		
        		
        		// add a row for every star result
        		while (rst.next()) {
        			// get a star from result set
        			ArrayList<String> movieName =new ArrayList<String>();
        			String starName = rst.getString("name");
        			String birthYear = rst.getString("birthyear");
        			
        			for(MovieListing movie:movies){
        				for(int i=0;i<movie.getStarID().size();i++)
        				{
        					if(movie.getStarID().get(i).equals(rst.getString("id")))
        					{
        						movieName.add(movie.getTitle());
        					}
        				}
        			}
        			
        			
        			out.println("<tr>");
        			out.println("<td><ul>");
        			for(int i=0; i< movieName.size();i++)
        			{
        				out.println("<li><a href=\"singleMovie.jsp?title="+ movieName.get(i) +"\">" + movieName.get(i)+"</a></li>");
        			}
        			out.println("</ul></td>");
        			out.println("<td>" + starName + "</td>");
        			out.println("<td>" + birthYear + "</td>");
        			out.println("</tr>");
        		}
        		
        		out.println("</table>");
        		
        		out.println("</center>");
        		out.println("</div>");
        		out.println("</body>");
        		
        		rst.close();
        		pst.close();
        		
        		
        } catch (Exception e) {
        		/*
        		 * After you deploy the WAR file through tomcat manager webpage,
        		 *   there's no console to see the print messages.
        		 * Tomcat append all the print messages to the file: tomcat_directory/logs/catalina.out
        		 * 
        		 * To view the last n lines (for example, 100 lines) of messages you can use:
        		 *   tail -100 catalina.out
        		 * This can help you debug your program after deploying it on AWS.
        		 */
        		e.printStackTrace();
        		
        		out.println("<body>");
        		out.println("<p>");
        		out.println("Exception in doGet: " + e.getMessage());
        		out.println("</p>");
        		out.print("</body>");
        }
        
        out.println("</html>");
        out.close();
        
	}


}

