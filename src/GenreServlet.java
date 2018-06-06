
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import helper.DBConnection;
import helper.MovieListing;

/**
 * Servlet implementation class GenreServlet
 */
@WebServlet("/GenreServlet")
public class GenreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	       
        String genreId = request.getParameter("id");
        System.out.println(genreId);
        ArrayList<MovieListing> movies=(ArrayList<MovieListing>) request.getSession().getAttribute("Movies");
        // set response mime type
        response.setContentType("text/html"); 

        // get the printwriter for writing response
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
      
        
                
        out.println("<head>");
        
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");

        
        out.println("<title>Movie List</title></head>");
        
        out.println("<%@include file=\"navbar.jsp\" %>");

        try {
        		
        		// create database connection
        		//Connection connection = DBConnection.getConnection();
        	
        	//P5 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			Context initCtx = new InitialContext();

            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            
            // Look up our data source
            DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
			
            Connection con=ds.getConnection();
			
            //P5~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
        		// prepare query
        		String query = "SELECT * from genres where id=?";
        		PreparedStatement pst=con.prepareStatement(query);
        		pst.setString(1, genreId);
        		// execute query
        		ResultSet rst=pst.executeQuery();
        		
        		out.println("<center>");

        		out.println("<body>");
        		out.println("<div class=\"container\">");

        		out.println("<h1>Details of Genre</h1>");
        		
        		out.println("<table>");
        		
        		// add table header row
        		out.println("<tr style=\"font-size: large; font-weight: bold\">");
        		out.println("<td>Movie List</td>");
        		out.println("<td>Genre Name</td>");
        		out.println("</tr>");
        		
        		
        		// add a row for every star result
        		while (rst.next()) {
        			// get a star from result set
        			ArrayList<String> movieName =new ArrayList<String>();
        			String genreName = rst.getString("name");
        			
        			for(MovieListing movie:movies){
        				for(int i=0;i<movie.getGenreID().size();i++)
        				{
        					if(movie.getGenreID().get(i).equals(rst.getString("id")))
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
        			out.println("<td>" + genreName + "</td>");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
