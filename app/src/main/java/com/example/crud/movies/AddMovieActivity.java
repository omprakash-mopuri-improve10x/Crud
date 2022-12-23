package com.example.crud.movies;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Movie");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String movieId = binding.movieIdTxt.getText().toString();
            SeriesItem series = (SeriesItem) binding.seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = binding.imageUrlTxt.getText().toString();
            String movieName = binding.movieNameTxt.getText().toString();
            String description = binding.descriptionTxt.getText().toString();
            addMovie(seriesId, movieId, movieName, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(String seriesId, String movieId, String title, String imageUrl, String description) {
        Movie movie = new Movie(seriesId, movieId, title, imageUrl, description);
        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showToast("Successfully added a movie");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Failed to add a movie");
            }
        });
    }
}
