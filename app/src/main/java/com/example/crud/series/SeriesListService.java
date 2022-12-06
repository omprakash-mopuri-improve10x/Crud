package com.example.crud.series;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SeriesListService {

    @GET("omSeries")
    Call<List<Series>> fetchSeriesList();

    @POST("omSeries")
    Call<Series> createSeries(@Body Series series);

    @DELETE("omSeries/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT("omSeries/{id}")
    Call<Void> EditSeries(@Path("id") String id, @Body Series series);
}
