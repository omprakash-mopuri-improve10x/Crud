package com.example.crud;

import com.google.gson.annotations.SerializedName;

public class Movie {

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
}
