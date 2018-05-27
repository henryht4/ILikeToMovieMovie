
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TitleSearchResult
 */
@WebServlet(name = "/TitleSearchResult")
public class SearchResults extends HttpServlet {
	
	private static final String TITLESEARCH = "/titleresult";
	private static final String YEARSEARCH = "/yearresult";
	private static final String DIRECTORSEARCH = "/directorresult";
	private static final String STARSEARCH = "/moviebystarresult";

	@Resource(name = "jdbc/moviedb")
    private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
    }
    
    @Override
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		processRequest(request, response);
       }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String path = request.getServletPath();
    	switch(path) {
    	case TITLESEARCH:
    		titlesearchfunction(request,response);
    		break;
    	case YEARSEARCH:
    		yearsearchfunction(request, response);
    		break;
    	case DIRECTORSEARCH:
    		directorsearchfunction(request,response);
    		break;
    	case STARSEARCH:
    		starsearchfunction(request,response);
    		break;
    	default:
    		break;
    	}
    }
    		
    void titlesearchfunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	
        // Building page head with title
        out.println("<html><head><title>Movie Titles Found:</title></head>");

        // Building page body
        out.println("<body><h1>Movie Titles Found:</h1>");


        try {

            // Create a new connection to database
            Connection dbCon = dataSource.getConnection();

            // Declare a new statement
            Statement statement = dbCon.createStatement();

            // Retrieve parameter "name" from the http request, which refers to the value of <input name="name"> in index.html
            String title = request.getParameter("title");
            String limit = request.getParameter("limit");
            
            // Generate a SQL query
            String query = String.format("SELECT * FROM movies WHERE title like '%s'", title);
           
            // Perform the query
            ResultSet rs = statement.executeQuery(query);

            // Create a html <table>
            out.println("<table border>");

            // Iterate through each row of rs and create a table row <tr>
            out.println("<tr><td>ID</td><td>Title</td><td>Year</td><td>Director</td><td>Genres</td><td>Stars</td>");
           
            while (rs.next()) {
                String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
                
             
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
			
        				//String.format("SELECT s.name"
        				//+ "FROM movies m, stars_in_movies sl, stars s"
        				//+ "WHERE sl.starId = s.id and sl.movieId = '%s' and m.id = '%s'", movieID, movieID);
        				
        		//execute StarQuery to make set
        		ResultSet StarSet = StarsStatement.executeQuery(StarQuery);
        		
        		while(StarSet.next()) {
        			ListOfStars = ListOfStars + StarSet.getString(1) + ", ";
        		}
        		
        		
                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", movieID, movieTitle,movieYear,movieDirector, ListOfGenres,ListOfStars));
                
                StarSet.close();
                StarsStatement.close();
                GenreSet.close();
                GenreStatement.close();
            }
            
            out.println("</table>");
            
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
    
    void yearsearchfunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	
        // Building page head with title
        out.println("<html><head><title>Movie Titles By Year Found:</title></head>");



        try {

            // Create a new connection to database
            Connection dbCon = dataSource.getConnection();

            // Declare a new statement
            Statement statement = dbCon.createStatement();

            // Retrieve parameter "name" from the http request, which refers to the value of <input name="name"> in index.html
            String year = request.getParameter("year");
            String limit = request.getParameter("limit");
            

            // Building page body
            out.println(String.format("<body><h1>Movies made in the Year %s</h1>", year));
            
            // Generate a SQL query
            String query = String.format("SELECT * FROM movies WHERE year = '%s' ", year);
           
            // Perform the query
            ResultSet rs = statement.executeQuery(query);

            // Create a html <table>
            out.println("<table border>");

            // Iterate through each row of rs and create a table row <tr>
            out.println("<tr><td>ID</td><td>Title</td><td>Year</td><td>Director</td>");
           
            while (rs.next()) {
                String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
           
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
        		
        		
                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", movieID, movieTitle,movieYear,movieDirector, ListOfGenres,ListOfStars));
                
                
                StarSet.close();
                StarsStatement.close();
                GenreSet.close();
                GenreStatement.close();
            }
            
            out.println("</table>");
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
    
    void directorsearchfunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	
        // Building page head with title
        out.println("<html><head><title>Movie Titles By Director Found</title></head>");


        try {

            // Create a new connection to database
            Connection dbCon = dataSource.getConnection();

            // Declare a new statement
            Statement statement = dbCon.createStatement();

            // Retrieve parameter "name" from the http request, which refers to the value of <input name="name"> in index.html
            String director = request.getParameter("director");
            String limit = request.getParameter("limit");
           
            // Generate a SQL query
            String query = String.format("SELECT * FROM movies WHERE director like '%s'", director);
           
            // Perform the query
            ResultSet rs = statement.executeQuery(query);
            
            
            // Building page body
            out.println(String.format("<body><h1>Movie Titles By Director '%s':</h1>", director));

            // Create a html <table>
            out.println("<table border>");

            // Iterate through each row of rs and create a table row <tr>
            out.println("<tr><td>ID</td><td>Title</td><td>Year</td><td>Director</td>");
           
            while (rs.next()) {
                String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
           
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
        		
        		
                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", movieID, movieTitle,movieYear,movieDirector, ListOfGenres,ListOfStars));
                
                
                StarSet.close();
                StarsStatement.close();
                GenreSet.close();
                GenreStatement.close();
            }
            out.println("</table>");


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

    void starsearchfunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();
	
        // Building page head with title
        out.println("<html><head><title>Movie Titles By Star Found</title></head>");


        try {

            // Create a new connection to database
            Connection dbCon = dataSource.getConnection();

            // Declare a new statement
            Statement statement = dbCon.createStatement();

            // Retrieve parameter "name" from the http request, which refers to the value of <input name="name"> in index.html
            String star = request.getParameter("star");
            String limit = request.getParameter("limit");
           
            // Generate a SQL query
            String query = String.format(" SELECT s.name, m.id, m.title, m.year,m.director "
            		+ "FROM movies m, stars s, stars_in_movies sl "
            		+ "WHERE m.id = sl.movieId and sl.starId = s.id and s.name like '%s'", star);
           
            // Perform the query
            ResultSet rs = statement.executeQuery(query);
            
            
            // Building page body
            out.println(String.format("<body><h1>Movie Titles With Star named: '%s':</h1>", star));

            // Create a html <table>
            out.println("<table border>");

            // Iterate through each row of rs and create a table row <tr>
            out.println("<tr><td>Star</td><td>Title</td><td>Year</td><td>Director</td>");
           
            while (rs.next()) {
                String movieStar = rs.getString("name");
                String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
           
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
        		
        		
                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", movieID, movieTitle,movieYear,movieDirector, ListOfGenres,ListOfStars));
                
                
                StarSet.close();
                StarsStatement.close();
                GenreSet.close();
                GenreStatement.close();
            }
            out.println("</table>");


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


}
