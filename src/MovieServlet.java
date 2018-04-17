import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MovieServlet
 */
@WebServlet("/ratings")

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//sql login
		String loginUser = "root";
        String loginPasswd = "hn55uhj";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";
        
       
        //set response mime time
        response.setContentType("text/html");
        
        //get writer
        PrintWriter out = response.getWriter();
        
        
        out.println("<html>");
        
        out.println("<img src=\"http://i64.tinypic.com/2wd3jo4.png\" style=\"width:auto;height:60px;\">");  
        
        out.println("<div class=\"w3-bar w3-black w3-padding-20\">");


        out.println("<a href=\"#\" class=\"w3-bar-item w3-button \">Home</a>");
        out.println("<a href=\"#\" class=\"w3-bar-item w3-button\">About</a>");
        out.println("<a href=\"#\" class=\"w3-bar-item w3-button\">Contact</a>");

		out.println("<center>");

		out.println("</center>");

        out.println("</div>");
        
        
        out.println("<head>");
        
        
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">");

        
        out.println("<title>I Like to Movie Movie</title></head>");
        
        
        try {
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
        	
        	//create database connection
        	Connection connection = DriverManager.getConnection(loginUrl,loginUser,loginPasswd);
        	
        	//declare statement
        	Statement statement = connection.createStatement();
        	
        	
        	//query for movie info and rating
        	String query = "SELECT m.id, m.title, m.year, m.director, r.rating "
        			+ "FROM movies m, ratings r "
        			+ "WHERE m.id = r.movieId ORDER BY r.rating DESC LIMIT 20 ";
        	

        	
        	//execute query
        	ResultSet resultSet = statement.executeQuery(query);      	
 
        	
    		out.println("<center>");

        	out.println("<body>");


        	out.println("<h1>Top 20 Movie Ratings</h1>");

        	out.println("<table>");
        	
        	//add table header
        	out.println("<tr>");
        	out.println("<td>Title</td>");
        	out.println("<td>Year</td>");
        	out.println("<td>Director</td>");
        	out.println("<td>List of Genres</td>");
        	out.println("<td>List of Stars</td>");
        	out.println("<td>Rating</td>");
        	out.println("</tr>");
        	
        	//add row for every star result
        	while(resultSet.next()) {
        		String id = resultSet.getString("id");
        		String movieID = resultSet.getString("title");
        		String movieYear = resultSet.getString("year");
        		String movieDirector = resultSet.getString("director");
        		String movieRating = resultSet.getString("rating");
        		
        		String movieListOfGenres = "";
        		
        		//create genre statement
        		Statement GenreStatement = connection.createStatement();
        		
            	//Create Genre Query
        		String GenreQuery = "SELECT g.name "
        				+ "FROM genres_in_movies gl, genres g, ("
        						+ "SELECT m.id "
        						+ "FROM movies m, ratings r "
        						+ "WHERE m.id = r.movieId ORDER BY r.rating DESC LIMIT 20)sub "
        					+ "WHERE gl.movieId = sub.id and gl.genreId = g.id and sub.id = \"" + id + "\"";
        		
        		//execute GenreQuery to make set
        		ResultSet GenreSet = GenreStatement.executeQuery(GenreQuery);
        		
        		while(GenreSet.next()) {
        			movieListOfGenres = movieListOfGenres + GenreSet.getString(1) + " ";
        		}
        		
        		String movieListOfStars = "";
        		
        		//create Stars Statement
        		Statement StarsStatement = connection.createStatement();
        		
        		//Create Star Query
        		String StarQuery = "SELECT s.name "
        				+ "FROM stars_in_movies sl, stars s, ("
						+ "SELECT m.id "
						+ "FROM movies m, ratings r "
						+ "WHERE m.id = r.movieId ORDER BY r.rating DESC LIMIT 20)sub "
					+ "WHERE sl.movieId = sub.id and sl.starId = s.id and sub.id = \"" + id + "\"";
        		
        		//execute StarQuery to make set
        		ResultSet StarSet = StarsStatement.executeQuery(StarQuery);
        		
        		while(StarSet.next()) {
        			movieListOfStars = movieListOfStars + StarSet.getString(1) + ", ";
        		}
        		
        		
        		
        		out.println("</tr>");
        		out.println("<td>" + movieID + "</td>");
    			out.println("<td>" + movieYear + "</td>");
    			out.println("<td>" + movieDirector + "</td>");
    			out.println("<td>" + movieListOfGenres + "</td>");
    			out.println("<td>" + movieListOfStars + "</td>");
    			out.println("<td>" + movieRating + "</td>");
    			out.println("</tr>");
        		
    			GenreStatement.close();
    			StarsStatement.close();
        	}
        	
    		out.println("</table>");
    		
    		out.println("</body>");
    		
    		out.println("</center>");

    	
    		resultSet.close();
    		statement.close();
    		connection.close();
    		
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

