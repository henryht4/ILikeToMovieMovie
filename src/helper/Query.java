package helper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.sql.DataSource;

import helper.Movie;
import helper.StarListing;
import helper.MovieListing;

public class Query {
	
	@Resource(name = "jdbc/moviedb")
    private DataSource dataSource;
	
	public ArrayList<Movie>getResults(Movie movie, String orderBy, String sort, String limit) throws Exception{
		ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
		try {
			Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();            
            String query = "SELECT m.id, m.title, m.year, m.director, r.rating "
            		+ "FROM movies m, ratings r "     		
            		+ "WHERE m.title = ? AND m.year = ? AND m.director = ? "
            		+ "OR m.title like ? OR m.year = ? OR m.director like ? ORDER BY ? ? LIMIT ?";
            PreparedStatement preparedQ = con.prepareStatement(query);
            preparedQ.setString(1, movie.getTitle());
            preparedQ.setInt(2, movie.getYear());
            preparedQ.setString(3, movie.getDirector());
            preparedQ.setString(4, movie.getTitle());
            preparedQ.setInt(5, movie.getYear());
            preparedQ.setString(6, movie.getDirector());
            preparedQ.setString(7, orderBy);
            preparedQ.setString(8, sort);
            preparedQ.setString(9, limit);
           
            // Perform the query
            ResultSet rs = statement.executeQuery(query);
            listOfMovies = getMovieList(rs);
            con.close();
            
		}catch(Exception e) {
			e.printStackTrace();
		}
		return listOfMovies;
	}
	

	public ArrayList<Movie>getMovieList(ResultSet rs) throws Exception{
		ArrayList<Movie> listOfMovies = new ArrayList<Movie>();
		while(rs.next()) {
			String movieID = rs.getString("id");
            String movieTitle = rs.getString("title");
            Integer movieYear = rs.getInt("year");
            String movieDirector = rs.getString("director");
            ArrayList<String>listOfGenres = new ArrayList<String>();
            listOfGenres = getGenres(movieID);
            ArrayList<StarListing> listOfStars = new ArrayList<StarListing>();
            listOfStars = getStars(movieID);
            Float movieRating = rs.getFloat("rating");
            Movie movie = new Movie(movieID, movieTitle, movieYear, movieDirector, listOfGenres, listOfStars, movieRating);
            listOfMovies.add(movie);
		}
		return listOfMovies;
	
	}
	
	
	public ArrayList<StarListing> getStars(String movieId) {
		ArrayList<StarListing> stars = new ArrayList<StarListing>();
		ResultSet rs = null;
		try {
			Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            
            String query = "SELECT s.id, s.name "
            		+ "FROM stars s, stars_in_movies sl"
    				+ "WHERE sl.starId = s.id  AND sl.movieId = " + movieId;
            PreparedStatement preparedQ = con.prepareStatement(query);
            rs = preparedQ.executeQuery();
    		while (rs.next()) {
    			String id = rs.getString("id");
    			String name = rs.getString("name");

    			StarListing star = new StarListing(id, name);
    			stars.add(star);
    		}
    		con.close();
    		statement.close();
    		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return stars;
	}

	public ArrayList<String> getGenres(String movieId) throws Exception{
		ArrayList<String> genres = new ArrayList<String>();
		try {
			Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = null;
            
            String query = "SELECT name "
            		+ "FROM genres g, genres_in_movies gl"
            		+ "WHERE gl.genreId = g.id AND gl.movieId = ?"
            		+ "Order by name ASC";
            
            PreparedStatement preparedQ = con.prepareStatement(query);
            preparedQ.setString(1, movieId);
            
            rs = preparedQ.executeQuery();
            while(rs.next()) {
            	genres.add(rs.getString("name"));
            }
            con.close();
            statement.close();
        
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	    return genres;
	}

	public ArrayList<Movie> browseByGenre(String genre, String orderBy, String sort, String limit, Integer offset) throws Exception{
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			Connection con = dataSource.getConnection();
			Statement statement = con.createStatement();
			String query = "SELECT * "
					+ "FROM movies m, genres_in_moves gl, genres g, ratings r "
					+ "WHERE r.movieId = m.id and gl.movieId = m.id and gl.genreId = g.id and g.name = ? "
					+ "ORDER BY ? ? LIMIT ? OFFSET ?";
			PreparedStatement preparedQ = con.prepareStatement(query);
			preparedQ.setString(1, genre);
			preparedQ.setString(2, orderBy );
			preparedQ.setString(3, sort);
			preparedQ.setString(4, limit);
			preparedQ.setInt(5, offset);
			
			ResultSet rs = preparedQ.executeQuery();
			movies = getMovieList(rs);
			con.close();
			statement.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return movies;
		
	}
	
	public ArrayList<Movie> browseByTitle(String character, String orderBy, String sort, String limit, String offset) throws Exception{
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			Connection con = dataSource.getConnection();
            Statement statement = con.createStatement();
            
            character = character + "%";
            
            String query = "SELECT * "
            		+ "FROM movies m, ratings r"
            		+ "WHERE r.movieId = m.id and m.title LIKE ? order by ? ? LIMIT ? OFFSET ?";
           
            PreparedStatement preparedQ = con.prepareStatement(query);
            preparedQ.setString(1, character);
            preparedQ.setString(2, orderBy);
            preparedQ.setString(3, sort);
            preparedQ.setString(4, limit);
            preparedQ.setString(5, offset);
            
            ResultSet rs = preparedQ.executeQuery();
            movies = getMovieList(rs);
            
            con.close();
            statement.close();
            
			}catch(Exception e) {
				e.printStackTrace();
			}
		return movies;
		
	}

	public ArrayList<String> Alphabet(){
		ArrayList<String> alphabet = new ArrayList<String>();
		String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(char c : alph.toCharArray()) {
			alphabet.add(Character.toString(c));
		}
		return alphabet;
	}
	
	public ArrayList<MovieListing> getStarsMovies(String id) throws Exception{
		ArrayList<MovieListing> movies = new ArrayList<MovieListing>();
		try {
			Connection con = dataSource.getConnection();
			String query = "SELECT id, title "
					+ "FROM movies m, stars_in_movies sl"
					+ "WHERE m.id = sl.movieId and sl.starId = ?";
		PreparedStatement preparedQ = con.prepareStatement(query);
		preparedQ.setString(1, id);
		
		ResultSet rs = preparedQ.executeQuery(query);
		while(rs.next()) {
			String movieId= rs.getString("id");
			String movieTitle = rs.getString("title");
			
			MovieListing movie = new MovieListing(movieId, movieTitle);
			movies.add(movie);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	public boolean verifyCC(String firstName, String lastName, String id, String expiration) throws Exception{
		Connection con = dataSource.getConnection();
		
		String query = "Select * "
				+ "FROM creditcards "
				+ "WHERE firstName = ? and lastName=? and id = ? and expiration = ?";
		PreparedStatement preparedQ = con.prepareStatement(query);
		preparedQ.setString(1, firstName);
		preparedQ.setString(2, lastName);
		preparedQ.setString(3, id);
		preparedQ.setString(4, expiration);
		ResultSet rs = preparedQ.executeQuery();
		boolean verified = false;
		if(rs.next())
			verified = true;
		return verified;
	}
	
	public int checkUser(String email, String password) throws Exception{
		
		Connection con = dataSource.getConnection();
		int custID = 0;
		
		String query = "SELECT * "
				+ "FROM customers"
				+ "WHERE email = ? and password = ?";
		
		PreparedStatement preparedQ = con.prepareStatement(query);
		preparedQ.setString(1,  email);
		preparedQ.setString(2, password);
		ResultSet rs = preparedQ.executeQuery();
		
		if(rs.next())
			custID = rs.getInt("id");
		
		con.close();
		return custID;
		
	}
	
	public void addToCart(String customerId, ArrayList<String> movieId) throws Exception {
		Connection con = dataSource.getConnection();
		Date current = new Date();
		DateFormat df = DateFormat.getDateTimeInstance();
		
		
		for(String id : movieId) {
			String query = "INSERT into sales values(null, ? , ?, ?);";
			PreparedStatement preparedQ = con.prepareStatement(query);
			preparedQ.setString(1, customerId);
			preparedQ.setString(2, id);
			preparedQ.setNString(3, df.format(current));
			
			preparedQ.executeUpdate();
		}
		
	}

}
	
	
