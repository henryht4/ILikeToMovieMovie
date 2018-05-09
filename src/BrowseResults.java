
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



@WebServlet(name = "/BrowseResults")
public class BrowseResults extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//private static final String BROWSETITLE = "/browsetitleresults";
	private static final String BROWSEGENRE = "/browsegenreresults";

	@Resource(name = "jdbc/moviedb")
    private DataSource dataSource;
    
    public BrowseResults() {
        super();
    }

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
		 HttpSession session = request.getSession(true); 
		 Query query = new Query();
		 
		 //command is 
		 String command = (String) session.getAttribute("command");
			if (command == null)
				command = "genre";
		
		//genreReq is
		String genreReq = request.getParameter("req");
		if (genreReq == null)
			genreReq = "genre";
		
		//genre grabs the genre from search form
		String genre = request.getParameter("genre");
		if (genre == null) {
			genre = "";
		}
			
		String currentGenre = (String) session.getAttribute("currentGenre");
		if (currentGenre == null) 
			currentGenre = genre;
		
		
		String sort =(String) session.getAttribute("sort");
		if (sort == null) {
			sort = "ASC";
		}
	
		String orderOn = request.getParameter("orderBy");
		String orderSession = (String) session.getAttribute("orderBy");
		
		if (orderOn == null & orderSession == null)
			orderSession = "title";
		else if (orderSession != null & orderOn != null) {
			if (orderSession.equals(orderOn)) {
				//if (sort.equals("ASC"))
				//	sort = "DESC";
				//else if (sort.equals("DESC"))
				//	sort="ASC";
			}
			else 
				orderSession = orderOn;			
		}
		else if(orderSession == null & orderOn != null)
			orderSession = orderOn;
				 
		String limit = request.getParameter("limit");
			if (limit == null) 
				limit = (String) session.getAttribute("limit");
				
			if (limit == null) 
				limit = "10";
								
			String page = request.getParameter("page");
			Integer pgNo= new Integer(1);
			if (page == null)
				pgNo = (Integer) session.getAttribute("page");
			else
				pgNo = Integer.parseInt(page);

			if (pgNo == null)
				pgNo = 1;
			
			
		if (!command.equals(genreReq) || pgNo < 1 || !currentGenre.equals(genre)) {
			pgNo = 1;
			command = genreReq;
			currentGenre = genre;
		}

	

		String paging = request.getParameter("paging");
		if (paging == null) {
			paging = "";
		}

		if (pgNo == 1 & paging.equals("previous"))
			paging = "";

		if (paging.equals("previous")) {
			pgNo = pgNo - 1;
		} else if (paging.equals("next")) {
			pgNo = pgNo + 1;
		}
			
		int offset = (pgNo - 1) * 10;
			
	
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
			

		try {
			//public ArrayList<Movie> browseByGenre(String genre, String orderBy, String sort, String limit, String offset)
			movies = query.browseByGenre(genre, orderSession, sort, limit, offset);
			if (movies.isEmpty()){
				pgNo = pgNo - 1;
				offset = (pgNo - 1) * 10;
				movies = query.browseByGenre(genre, orderSession, sort, limit, offset);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(movies.get(1).getTitle());
			
		
		session.setAttribute("page", pgNo);
		session.setAttribute("movies", movies);
		session.setAttribute("command", command);
		session.setAttribute("genre", genre);
		session.setAttribute("orderBy", orderSession);
		session.setAttribute("sort",sort);
		session.setAttribute("currentGenre", currentGenre);
		session.setAttribute("limit", limit);
		request.getRequestDispatcher("browseGenreDisplay.jsp").forward(request, response);
		}		

	 }



        
        /*
        // Building page head with title

       
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

*/
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
