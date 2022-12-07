package com.example.crud.movies;

import com.example.crud.Constants;
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

    @GET(Constants.MOVIE_END_POINT)
    Call<List<Movie>> fetchMovies();

    @POST(Constants.MOVIE_END_POINT)
    Call<Movie> createMovie(@Body Movie movie);

    @DELETE(Constants.MOVIE_END_POINT + "/{id}")
    Call<Void> deleteMovie(@Path("id") String id);

    @PUT(Constants.MOVIE_END_POINT + "/{id}")
    Call<Void> editMovie(@Path("id") String id, @Body Movie movie);
}