package com.example.crud.series;

import com.example.crud.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SeriesListService {

    @GET(Constants.SERIES_END_POINT)
    Call<List<Series>> fetchSeriesList();

    @POST(Constants.SERIES_END_POINT)
    Call<Series> createSeries(@Body Series series);

    @DELETE(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> deleteSeries(@Path("id") String id);

    @PUT(Constants.SERIES_END_POINT + "/{id}")
    Call<Void> EditSeries(@Path("id") String id, @Body Series series);
}
