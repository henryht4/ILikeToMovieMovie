package com.example.along.myfablix;

import java.io.Serializable;
import java.util.ArrayList;

class MovieResult implements Serializable {
    String id;
    String title;
    Integer year;
    String director;
    Double rating;
    ArrayList<String> starName;
    ArrayList<String> genres;

    public MovieResult(String id, String title, Integer year, String director, Double rating){
        this.director = director;
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.year = year;
        starName= new ArrayList<String>();
        genres= new ArrayList<String>();
    }
    public ArrayList<String> getStarName() {
        return starName;
    }

    public void setStarName(ArrayList<String> starName) {
        this.starName = starName;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
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

    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
}
