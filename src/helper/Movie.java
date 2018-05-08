package helper;

import java.util.ArrayList;

public class Movie {
	
	String id;
	String title;
	Integer year;
	String director;
	private ArrayList<String> listOfGenres;
	private ArrayList<StarListing> listOfStars;
	Float rating;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
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
	
	public Float getRating() {
		return rating;
	}
	
	public void setRating(Float rating) {
		this.rating = rating;
	}
	
	public Movie(String id, String title, Integer year, String director, ArrayList<String> genres, ArrayList<StarListing> stars, Float rating) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.listOfGenres = genres;
		this.listOfStars= stars;
		this.rating = rating;
	}
	
}
