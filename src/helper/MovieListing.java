package helper;

import java.util.ArrayList;

public class MovieListing {
	String id;
	String title;
	Integer year;
	String director;
	ArrayList<String> starName;
	String name;
	String starid;
	ArrayList<String> starID;
	ArrayList<String> genres;
	String genreid;
	ArrayList<String> genreID;
	
	Float rating;
	
	public MovieListing()
	{
		starName= new ArrayList<String>();
		genres= new ArrayList<String>();
	}
	
	
	public String getGenreid() {
		return genreid;
	}

	public void setGenreId(String genreid) {
		this.genreid = genreid;
	}

	public ArrayList<String> getGenreID() {
		return genreID;
	}

	public void setGenreID(ArrayList<String> genreID) {
		this.genreID = genreID;
	}

	public String getStarid() {
		return starid;
	}

	public void setStarid(String starid) {
		this.starid = starid;
	}
	
 	public ArrayList<String> getStarID() {
		return starID;
	}

	public void setStarID(ArrayList<String> starID) {
		this.starID = starID;
	}
	
	
	public ArrayList<String> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public ArrayList<String> getStarName() {
		return starName;
	}

	public void setStarName(ArrayList<String> starName) {
		this.starName = starName;
	}

	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}

}
