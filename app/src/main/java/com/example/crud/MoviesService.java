package com.example.crud;

import com.example.crud.series.Series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MoviesService {

    @GET("omMovies")
    Call<List<Movie>> fetchMovies();

    @POST("omMovies")
    Call<Movie> createMovie(@Body Movie movie);
}
