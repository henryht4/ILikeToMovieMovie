package com.example.along.myfablix;

import java.net.MalformedURLException;
import java.net.URL;



public class Movie {
    private String id;
    private String title;

    public Movie(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }


}

