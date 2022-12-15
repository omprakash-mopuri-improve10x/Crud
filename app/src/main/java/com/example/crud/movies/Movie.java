package com.example.crud.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    public String seriesId;
    public String movieId;
    @SerializedName("name")
    public String title;
    public String imageUrl;
    public String description;

    public Movie() {
    }

    public Movie(String seriesId, String movieId, String title, String imageUrl, String description) {
        this.seriesId = seriesId;
        this.movieId = movieId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
    }
}
