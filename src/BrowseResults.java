
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import helper.Movie;
import helper.StarListing;
import helper.Query;



/**
 * Servlet implementation class GenreSearchResults
 */
@WebServlet(name = "/BrowseResults")
public class BrowseResults extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

	//private static final String BROWSETITLE = "/browsetitleresults";
	private static final String BROWSEGENRE = "/browsegenreresults";

	@Resource(name = "jdbc/moviedb")
    private DataSource dataSource;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	    	String path = request.getServletPath();
	    	switch(path) {
	    	//case BROWSETITLE:
	    	//	browsetitlefunction(request,response);
	    	//	break;
	    	case BROWSEGENRE:
	    		browsegenrefunction(request, response);
	    		break;
	    	default:
	    		break;
	    	}
	    }
	    
	 void browsegenrefunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		 RequestDispatcher dispatch = request.getRequestDispatcher("browseGenreDisplay.jsp");
		 dispatch.forward(request,response);
		 response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        //css
        out.println("<HTML><head>"+
        		"<link rel=\"stylesheet\" href=\"style.css\">" + 
        		"</head>"
        		);
        
        
        // Building page head with title
        out.println("<title>Movies By Genre Found:</title>");


       
        try {

            // Create a new connection to database
            Connection dbCon = dataSource.getConnection();

            // Declare a new statement
            Statement statement = dbCon.createStatement();

            // Retrieve parameter "name" from the http request, which refers to the value of <input name="name"> in index.html
            String genre = request.getParameter("genre");
            

            // Generate a SQL query
            String query = String.format("SELECT * "
            		+ "FROM movies m, genres_in_movies gl, genres g, ratings r "
            		+ "WHERE r.movieId = m.id and m.id = gl.movieId and gl.genreId = g.id and g.name = '%s' LIMIT 10 OFFSET 0", genre);
            
         
            
            // Perform the query
            ResultSet rs = statement.executeQuery(query);
            
            out.println(String.format("<center><div>"));

            // Create a html <table>
            out.println("<table>");

            // Building page body
            out.println("<body><h>Movies By Genre</h>");
            
            out.println("<div class=\"div1\">");

            out.println(String.format("<h2>'%s' Movies Found:</h2>", genre));
            
            // Iterate through each row of rs and create a table row <tr>
            out.println("<tr><td>ID</td><td>Title</td><td>Year</td><td>Director</td><td>Genres</td><td>Stars</td><td>Rating</td></tr>");
            while (rs.next()) {
                String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
                String movieGenre = rs.getString("name");
                String movieRating = rs.getString("rating");
                
              //create genre statement
                String ListOfGenres = "";
                Statement GenreStatement = dbCon.createStatement();
                
                //Create Genre Query
        		String GenreQuery = String.format("select g.name from movies m, genres_in_movies gl, genres g where gl.genreId = g.id and gl.movieId = '%s' and m.id = '%s'",movieID,movieID);
        					
        		//execute GenreQuery to make set
        		ResultSet GenreSet = GenreStatement.executeQuery(GenreQuery);
        		
        		while(GenreSet.next()) {
        			ListOfGenres = ListOfGenres + GenreSet.getString(1) + " ";
        		}
        		
        		//List of Stars creation
        		String ListOfStars = "";
        		
        		//create Stars Statement
        		Statement StarsStatement = dbCon.createStatement();
        		
        		//Create Star Query
        		String StarQuery = String.format("select s.name from movies m, stars_in_movies sl, stars s where sl.starId = s.id and sl.movieId = '%s' and m.id = '%s'",movieID,movieID);
	
        		//execute StarQuery to make set
        		ResultSet StarSet = StarsStatement.executeQuery(StarQuery);
        		
        		while(StarSet.next()) {
        			ListOfStars = ListOfStars + StarSet.getString(1) + ", ";
        		}
        		
        		
                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", movieID, movieTitle,movieYear,movieDirector, ListOfGenres,ListOfStars, movieRating));
                out.println(String.format("</div></center>"));
                StarSet.close();
                StarsStatement.close();
                GenreSet.close();
                GenreStatement.close();
            }

            out.println("</table>");

            out.println("</div>");

            out.println("</HTML>");
            
            // Close all structures
            rs.close();
            statement.close();
            dbCon.close();
            

        } catch (Exception ex) {

            // Output Error Massage to html
            out.println(String.format("<html><head><title>Error</title></head>\n<body><p>SQL error in doGet: %s</p></body></html>", ex.getMessage()));
            return;
        }
        out.close();
    }


/*
	 void browsetitlefunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			response.setContentType("text/html");    // Response mime type

			
			
	        // Output stream to STDOUT
	        PrintWriter out = response.getWriter();

	        //css
	        out.println("<HTML><head>"+
	        		"<link rel=\"stylesheet\" href=\"style.css\">" + 
	        		"</head>"
	        		);
	        
	        
	        // Building page head with title
	        out.println("<title>Movies By Letter:</title>");

	    

	        try {

	            // Create a new connection to database
	            Connection dbCon = dataSource.getConnection();

	            // Declare a new statement
	            Statement statement = dbCon.createStatement();

	            // Retrieve parameter "name" from the http request, which refers to the value of <input name="name"> in index.html
	            String letter = request.getParameter("letter");
	            String limit = request.getParameter("limit");
	            String order = request.getParameter("order");

	            // Generate a SQL query
	            String query = String.format("SELECT * "
	            		+ "FROM movies m, ratings r "
	            		+ "WHERE r.movieId = m.id and m.title like '%s' ORDER BY r.rating %s LIMIT %s OFFSET 0", letter, order, limit );
	            




	            // Perform the query
	            ResultSet rs = statement.executeQuery(query);

	            out.println(String.format("<center><div>"));

		        // Building page body
		        out.println("<body><h>Movies By Letter</h>");
		        

	            out.println("<div class=\"div1\">");

	            out.println(String.format("<h2> Movies Starting with %s :</h2>", letter));
	            

	            // Create a html <table>
	            
	            out.println("<table>");
	            	
	            // Iterate through each row of rs and create a table row <tr>
	            out.println("<tr><td>ID</td><td>Title</td><td>Year</td><td>Director</td><td>Genres</td><td>Stars</td><td>Rating</td></tr>");
	            while (rs.next()) {
	                String movieID = rs.getString("id");
	                String movieTitle = rs.getString("title");
	                String movieYear = rs.getString("year");
	                String movieDirector = rs.getString("director");
	                String movieRating = rs.getString("rating");
	                
	              //create genre statement
	                String ListOfGenres = "";
	                Statement GenreStatement = dbCon.createStatement();
	                
	                //Create Genre Query
	        		String GenreQuery = String.format("select g.name from movies m, genres_in_movies gl, genres g where gl.genreId = g.id and gl.movieId = '%s' and m.id = '%s'",movieID,movieID);
	        					
	        		//execute GenreQuery to make set
	        		ResultSet GenreSet = GenreStatement.executeQuery(GenreQuery);
	        		
	        		while(GenreSet.next()) {
	        			ListOfGenres = ListOfGenres + GenreSet.getString(1) + " ";
	        		}
	        		
	        		//List of Stars creation
	        		String ListOfStars = "";
	        		
	        		//create Stars Statement
	        		Statement StarsStatement = dbCon.createStatement();
	        		
	        		//Create Star Query
	        		String StarQuery = String.format("select s.name from movies m, stars_in_movies sl, stars s where sl.starId = s.id and sl.movieId = '%s' and m.id = '%s'",movieID,movieID);
		
	        		//execute StarQuery to make set
	        		ResultSet StarSet = StarsStatement.executeQuery(StarQuery);
	        		
	        		while(StarSet.next()) {
	        			ListOfStars = ListOfStars + StarSet.getString(1) + ", ";
	        		}
	        		
	        		
	                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", movieID, movieTitle,movieYear,movieDirector, ListOfGenres,ListOfStars, movieRating));
	                
	                StarSet.close();
	                StarsStatement.close();
	                GenreSet.close();
	                GenreStatement.close();
	            }

	            out.println("</table>");

	            out.println("</div>");

	            out.println("</HTML>");

	            // Close all structures
	            rs.close();
	            statement.close();
	            dbCon.close();
	            

	        } catch (Exception ex) {

	            // Output Error Massage to html
	            out.println(String.format("<html><head><title>Error</title></head>\n<body><p>SQL error in doGet: %s</p></body></html>", ex.getMessage()));
	            return;
	        }
	        
	        
	        
	        out.close();
	    }

*/
}