
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
    	//case BROWSETITLE:
    		//browsetitlefunction(request,response);
    		//break;
    	case BROWSEGENRE:
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
		 HttpSession session = request.getSession(true);
		 ArrayList<Movie> movies = new ArrayList<Movie>();
		 ArrayList<String> genres = new ArrayList<String>();
         ArrayList<StarListing> stars = new ArrayList<StarListing>();
		//ArrayList<Movie> outTry = new ArrayList<Movie>();
	//	ArrayList<String> genres = new ArrayList<String>();
	//	ArrayList<StarListing> stars = new ArrayList<StarListing>();
		try {

			String limit = request.getParameter("limit");
			if (limit == null) 
				limit = (String) session.getAttribute("limit");
			
			if (limit == null) 
				limit = "10";
			
			String sortReq = request.getParameter("sort");
			String sort =(String) session.getAttribute("sort");
			if (sort == null) {
				sort = "ASC";
			}
			String orderBy = (String) session.getAttribute("orderBy");
		
			if (sortReq == null & orderBy == null)
				orderBy = "title";
			else if (orderBy != null & sortReq != null) {
				if (orderBy.equals(sortReq)) {
					if (sort.equals("ASC"))
						sort = "DESC";
					else if (sort.equals("DESC"))
						sort="ASC";
				}
				else 
					orderBy = sortReq;
					
			}
			else if(orderBy == null & sortReq != null)
				orderBy = sortReq;

			String command = (String) session.getAttribute("command");
			if (command == null)
				command = "genre";
			String req = request.getParameter("req");
			if (req == null)
				req = "genre";
			
			String genre = request.getParameter("genre");
			if (genre == null) {
				genre = "";
			}
			String currentGenre = (String) session.getAttribute("currentGenre");
			if (currentGenre == null) 
				currentGenre = genre;
			
			String p = request.getParameter("page");
			Integer page = new Integer(1);
			if (p == null)
				page = (Integer) session.getAttribute("page");
			else
				page = Integer.parseInt(p);

			if (page == null)
				page = 1;
			
			
			if (!command.equals(req) || page < 1 || !currentGenre.equals(genre)) {
				page = 1;
				command = req;
				currentGenre = genre;
			}


			String paging = request.getParameter("paging");
			if (paging == null) {
				paging = "";
			}

			if (page == 1 & paging.equals("previous"))
				paging = "";

			if (paging.equals("previous")) {
				page = page - 1;
			} else if (paging.equals("next")) {
				page = page + 1;
			}
			
			int offset = (page - 1) * 10;
			
			
			System.out.println("TRY BROWSERESULTS.JAVA");
			Connection con = dataSource.getConnection();
			Statement statement = con.createStatement();
			
//			String genre = request.getParameter("genre");
//			StarListing s = new StarListing("id1", "JACKIE CHAN");
//			genres.add("HORROR");
//			genres.add("COMEDY");
//			genres.add("ANIME");
//			Movie m = new Movie("id1", "Title1", "2017", "Director", "10", genres, stars);
//			outTry.add(m);
//			inTry.add(m);

			String query = String.format("SELECT * "
            		+ "FROM movies m, genres_in_movies gl, genres g, ratings r "
            		+ "WHERE r.movieId = m.id and m.id = gl.movieId and gl.genreId = g.id and g.name = '%s' LIMIT %s OFFSET %s", genre, limit, offset);
            
			ResultSet rs = statement.executeQuery(query);
			
			while(rs.next()) {
				String movieID = rs.getString("id");
                String movieTitle = rs.getString("title");
                String movieYear = rs.getString("year");
                String movieDirector = rs.getString("director");
                String movieRating = rs.getString("rating");
                
               
                Statement GenreStatement = con.createStatement();
                String GenreQuery = String.format("select g.name from movies m, genres_in_movies gl, genres g where gl.genreId = g.id and gl.movieId = '%s' ",movieID);
                ResultSet GenreSet = GenreStatement.executeQuery(GenreQuery);
                ArrayList<String> whileGenre = new ArrayList<String>();
                while(GenreSet.next()) {
        			whileGenre.add(GenreSet.getString("name"));
        			
        		}
                genres.addAll(whileGenre);
        		Statement StarsStatement = con.createStatement();
        		String StarQuery = String.format("select s.id, s.name from movies m, stars_in_movies sl, stars s where sl.starId = s.id and sl.movieId = '%s' and m.id = '%s'",movieID,movieID);
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
			session.setAttribute("command", command);
			session.setAttribute("genre", genre);
			session.setAttribute("sort", sort);
			session.setAttribute("orderBy",orderBy);
			session.setAttribute("genres", genres);
			session.setAttribute("currentGenre", currentGenre);
			session.setAttribute("limit", limit);
			session.setAttribute("stars",stars);
			request.getRequestDispatcher("browseGenreDisplay.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
	

	/*
	 void browsegenrefunction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		 HttpSession session = request.getSession(true); 
		 System.out.println("browsegenrefunction servlet called");
		
		 Query query = new Query();
			
			String limit = request.getParameter("limit");
			if (limit == null) 
				limit = (String) session.getAttribute("limit");
			
			if (limit == null) 
				limit = "10";
			
			String sortReq = request.getParameter("sort");
			String sort =(String) session.getAttribute("sort");
			if (sort == null) {
				sort = "ASC";
			}
			String orderBy = (String) session.getAttribute("orderBy");
		
			if (sortReq == null & orderBy == null)
				orderBy = "title";
			else if (orderBy != null & sortReq != null) {
				if (orderBy.equals(sortReq)) {
					if (sort.equals("ASC"))
						sort = "DESC";
					else if (sort.equals("DESC"))
						sort="ASC";
				}
				else 
					orderBy = sortReq;
					
			}
			else if(orderBy == null & sortReq != null)
				orderBy = sortReq;

			String command = (String) session.getAttribute("command");
			if (command == null)
				command = "genre";
			String req = request.getParameter("req");
			if (req == null)
				req = "genre";
			
			String genre = request.getParameter("genre");
			if (genre == null) {
				genre = "";
			}
			String currentGenre = (String) session.getAttribute("currentGenre");
			if (currentGenre == null) 
				currentGenre = genre;
			
			String p = request.getParameter("page");
			Integer page = new Integer(1);
			if (p == null)
				page = (Integer) session.getAttribute("page");
			else
				page = Integer.parseInt(p);

			if (page == null)
				page = 1;
			
			
			if (!command.equals(req) || page < 1 || !currentGenre.equals(genre)) {
				page = 1;
				command = req;
				currentGenre = genre;
			}


			String paging = request.getParameter("paging");
			if (paging == null) {
				paging = "";
			}

			if (page == 1 & paging.equals("previous"))
				paging = "";

			if (paging.equals("previous")) {
				page = page - 1;
			} else if (paging.equals("next")) {
				page = page + 1;
			}
			
			int offset = (page - 1) * 10;
			ArrayList<String> genres = new ArrayList<String>();
			ArrayList<Movie> movies = new ArrayList<Movie>();
			

			try {
				//String genre, String orderBy, String sort, String limit, Integer offset
				movies = query.browseByGenre(genre, orderBy, sort , limit, offset);
				//genres = query.getGenres();	
				if (movies.isEmpty()){
					page = page - 1;
					offset = (page - 1) * 10;
					movies = query.browseByGenre(genre, orderBy, sort , limit, offset);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
	
			session.setAttribute("page", page);
			session.setAttribute("movies", movies);
			session.setAttribute("command", command);
			session.setAttribute("genre", genre);
			session.setAttribute("sort", sort);
			session.setAttribute("orderBy",orderBy);
			session.setAttribute("genres", genres);
			session.setAttribute("currentGenre", currentGenre);
			session.setAttribute("limit", limit);
			request.getRequestDispatcher("browseGenreDisplay.jsp").forward(request, response);
		}		 
}
	*/