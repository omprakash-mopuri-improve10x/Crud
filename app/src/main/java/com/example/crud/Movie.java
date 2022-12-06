package com.example.crud;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    public String seriesId;
    public String movieId;
    public String imageUrl;
    @SerializedName("name")
    public String title;
    public String description;

    public Movie() {

    }

    public Movie(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public Movie(String movieId, String seriesId, String imageUrl, String title, String description) {
        this.movieId = movieId;
        this.seriesId = seriesId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }
}
