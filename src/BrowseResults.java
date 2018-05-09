
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
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
	private static final String BROWSEGENRE = "/browsegenreresults";
	private static final String BROWSETITLE = "/browsetitleresults";
	
	@Resource(name = "jdbc/moviedb")
    private DataSource dataSource;
    
    public BrowseResults() {
        super();
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String path = request.getServletPath();
    	switch(path) {
    	case BROWSETITLE:
    		browsetitlefunction(request,response);
    		break;
    	case BROWSEGENRE:
    		System.out.println("PROCESS REQUEST");
    		browsegenrefunction(request, response);
    		break;
    	default:
    		break;
    	}
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}
	
	void browsegenrefunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		System.out.println("BROWSEGENREFUNCTION");
		 HttpSession session = request.getSession(true);
		 ArrayList<Movie> movies = new ArrayList<Movie>();
		 ArrayList<String> genres = new ArrayList<String>();
         ArrayList<StarListing> stars = new ArrayList<StarListing>();
		try {

			String genre = request.getParameter("genre");

			String orderBy = request.getParameter("orderBy");
			
			String sort = request.getParameter("sort");


			String limit = request.getParameter("limit");
			if (limit == null) 
				limit = (String) session.getAttribute("limit");
			if (limit == null) 
				limit = "10";

			
			String p = request.getParameter("page");
			Integer page = new Integer(1);
			if (p == null)
				page = (Integer) session.getAttribute("page");
			else
				page = Integer.parseInt(p);

			if (page == null)
				page = 1;
			
			int offset = (page - 1) * 10;
			
	
			Connection con = dataSource.getConnection();
			Statement statement = con.createStatement();
			
			String query = String.format("SELECT m.id, m.title, m.year, m.director, r.rating "
            		+ "FROM movies m, genres_in_movies gl, genres g, ratings r "
            		+ "WHERE r.movieId = m.id and m.id = gl.movieId and gl.genreId = g.id and g.name = '%s' ORDER BY %s %s LIMIT %s OFFSET %s", genre, orderBy, sort, limit, offset);
            
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
                String movieRating = rs.getString("rating");
                
               
                Statement GenreStatement = con.createStatement();
                String GenreQuery = String.format("select g.name from movies m, genres_in_movies gl, genres g where gl.genreId = g.id and gl.movieId = m.id and m.id = '%s'",movieID);
                ResultSet GenreSet = GenreStatement.executeQuery(GenreQuery);
                
                while(GenreSet.next()) {
        			genres.add(GenreSet.getString("name"));
        			
        		}
                
        		Statement StarsStatement = con.createStatement();
        		String StarQuery = String.format("select s.id, s.name from movies m, stars_in_movies sl, stars s where sl.starId = s.id and sl.movieId = m.id and m.id = '%s'",movieID);
        		ResultSet StarSet = StarsStatement.executeQuery(StarQuery);
        		while(StarSet.next()) {
        			StarListing s = new StarListing(StarSet.getString("id"), StarSet.getString("name"));
        			stars.add(s);
        		}
        		
        		Movie movie = new Movie(movieID, movieTitle, movieYear, movieDirector, movieRating, genres, stars);
    			movies.add(movie);
    			
			}
			session.setAttribute("page", page);
			session.setAttribute("movies", movies);
			session.setAttribute("genre", genre);
			session.setAttribute("sort", sort);
			session.setAttribute("orderBy",orderBy);
			session.setAttribute("genres", genres);
			session.setAttribute("limit", limit);
			session.setAttribute("stars",stars);
		
			request.getRequestDispatcher("browseGenreDisplay.jsp").forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	void browsetitlefunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		 HttpSession session = request.getSession(true);
		 ArrayList<Movie> movies = new ArrayList<Movie>();
		 ArrayList<String> genres = new ArrayList<String>();
         ArrayList<StarListing> stars = new ArrayList<StarListing>();
		try {

			String letter = request.getParameter("letter");

			String orderBy = request.getParameter("orderBy");
			
			String sort = request.getParameter("sort");
			if(sort == null)
				sort ="asc";


			String limit = request.getParameter("limit");
			if (limit == null) 
				limit = (String) session.getAttribute("limit");
			if (limit == null) 
				limit = "10";

			
			String p = request.getParameter("page");
			Integer page = new Integer(1);
			if (p == null)
				page = (Integer) session.getAttribute("page");
			else
				page = Integer.parseInt(p);

			if (page == null)
				page = 1;
			
			int offset = (page - 1) * 10;
			
	
			Connection con = dataSource.getConnection();
			Statement statement = con.createStatement();
			
			String query = String.format("SELECT m.id, m.title, m.year, m.director, r.rating "
            		+ "FROM movies m, genres_in_movies gl, genres g, ratings r "
            		+ "WHERE r.movieId = m.id and m.id = gl.movieId and gl.genreId = g.id and m.title like '%s' ORDER BY %s %s LIMIT %s OFFSET %s",letter, orderBy, sort, limit, offset);
            
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
                String movieRating = rs.getString("rating");
                
               
                Statement GenreStatement = con.createStatement();
                String GenreQuery = String.format("select g.name from movies m, genres_in_movies gl, genres g where gl.genreId = g.id and gl.movieId = m.id and m.id = '%s'",movieID);
                ResultSet GenreSet = GenreStatement.executeQuery(GenreQuery);
                
                while(GenreSet.next()) {
        			genres.add(GenreSet.getString("name"));
        			
        		}
                
        		Statement StarsStatement = con.createStatement();
        		String StarQuery = String.format("select s.id, s.name from movies m, stars_in_movies sl, stars s where sl.starId = s.id and sl.movieId = m.id and m.id = '%s'",movieID);
        		ResultSet StarSet = StarsStatement.executeQuery(StarQuery);
        		while(StarSet.next()) {
        			StarListing s = new StarListing(StarSet.getString("id"), StarSet.getString("name"));
        			stars.add(s);
        		}
        		
        		Movie movie = new Movie(movieID, movieTitle, movieYear, movieDirector, movieRating, genres, stars);
    			movies.add(movie);
    			
			}
			session.setAttribute("page", page);
			session.setAttribute("movies", movies);
			session.setAttribute("letter", letter);
			session.setAttribute("sort", sort);
			session.setAttribute("orderBy",orderBy);
			session.setAttribute("genres", genres);
			session.setAttribute("limit", limit);
			session.setAttribute("stars",stars);
		
			request.getRequestDispatcher("browseGenreDisplay.jsp").forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
	