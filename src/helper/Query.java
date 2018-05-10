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

public class Query{
	
	@Resource(name = "jdbc/moviedb")
    private DataSource dataSource;
	
	public ArrayList<Movie> getMovieData(ResultSet result) throws Exception {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		while (result.next()) {
			String id = result.getString(1);
			String title = result.getString(2);
			String year = result.getString(3);
			String director = result.getString(4);
			String rating = result.getString(5);	
			ArrayList<String> genres = new ArrayList<String>();
			genres = getGenres(id);
			ArrayList<StarListing> stars = new ArrayList<StarListing>();
			stars = getStars(id);
			Movie movie = new Movie(id, title, year, director, rating, genres, stars);
			movies.add(movie);
		}
		return movies;
	}
	
	public ArrayList<String> getGenres(String movieID) throws Exception {
		ResultSet result = null;
		ArrayList<String> genres = new ArrayList<String>();

		Connection con = dataSource.getConnection();

		String str = "SELECT name FROM genres JOIN genres_in_movies "
				+ "ON genres_in_movies.genre_id = genres.id  WHERE genres_in_movies.movie_id = ?";
		PreparedStatement query = con.prepareStatement(str);
		query.setString(1, movieID);
		result = query.executeQuery();
		while (result.next()) {
			genres.add(result.getString(1));
		}
		con.close();

		return genres;
	}
	
	public ArrayList<StarListing> getStars(String movieID) throws Exception {

		ArrayList<StarListing> stars = new ArrayList<StarListing>();

		Connection con = dataSource.getConnection();

		String str = "SELECT s.id, s.name "
				+ "FROM movies m, stars s, stars_in_movies sl"
				+ "WHERE m.id = sl.movieId and s.id = sl.starId and m.id = " + movieID;
		Statement query = con.createStatement();
		ResultSet result = query.executeQuery(str);
		while (result.next()) {
			String id = result.getString(1);
			String name = result.getString(2);
			
			StarListing star = new StarListing(id, name);
			stars.add(star);
		}
		con.close();
		return stars;
	}

	public ArrayList<Movie> browseByGenre(String genre, String orderBy, String sort, String limit, Integer offset)throws Exception {
		System.out.println("QUERY.BROWSEBYGENREFUNCTION");
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			System.out.println("TRY");
			Connection con = dataSource.getConnection();
			Statement statement = con.createStatement();
		
			/*
		String str = "SELECT m.id, m.title, m.year, m.director, r.rating"
				+ "FROM movies m genres_in_movies gl, genres g, ratings r"
				+ "WHERE m.id = gl.movieId and g.id = gl.movieId and g.name = \" "+ genre + " \" "
				+ "ORDER BY \" "+orderBy + " \" " + " \" "+sort + "\" LIMIT " + limit + "\"  OFFSET " + offset;
				*/
			String query = "SELECT * FROM movies limit 10";
			ResultSet rs = statement.executeQuery(query);
			System.out.println("RESULTSET: ");
		
		/*
		query.setString(1, genre);
		query.setString(2, orderBy);
		query.setString(3,sort);
		query.setString(4, limit);
		query.setInt(5, offset);
		System.out.println("BEFORE RESULT:");
		ResultSet result = query.executeQuery();
		result.next();
		*/
		
		//movies = getMovieData(result);
		
		return movies;
/*		ResultSet rs = preparedQ.executeQuery(query);
		while(rs.next()) {
			String movieId= rs.getString("id");
			String movieTitle = rs.getString("title");
			
			MovieListing movie = new MovieListing();
			movies.add(movie);
			}
*/
		}catch(Exception e) {
			e.printStackTrace();
		}
		return movies;
	}
}

