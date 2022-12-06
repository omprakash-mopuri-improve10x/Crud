package com.example.crud.movies;

import com.example.crud.movies.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MoviesService {

    @GET("omMovies")
    Call<List<Movie>> fetchMovies();

    @POST("omMovies")
    Call<Movie> createMovie(@Body Movie movie);

    @DELETE("omMovies/{id}")
    Call<Void> deleteMovie(@Path("id") String id);

    @PUT("omMovies/{id}")
    Call<Void> editMovie(@Path("id") String id, @Body Movie movie);
}
