package helper;

import java.util.ArrayList;

public class Movie {
	
	private String id;
	private String title;
	private String year;
	private String director;
	private ArrayList<String> listOfGenres;
	private ArrayList<StarListing> listOfStars;
	private String rating;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
	public ArrayList<StarListing> getStars() { 
		return listOfStars; 
	}
	
	public ArrayList<String> getGenres(){ 
		return listOfGenres; 
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public Movie(String id, String title, String year, String director, String rating, ArrayList<String> genres, ArrayList<StarListing> stars) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.rating = rating;
		this.director = director;
		this.listOfGenres = genres;
		this.listOfStars= stars;
		
	}
	
}
