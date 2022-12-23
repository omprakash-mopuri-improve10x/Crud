package com.example.crud.movies;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.series.SeriesItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMovieActivity extends BaseAddEditMovieActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Movie");
        movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
        showData();
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
            updateMovie(movie.id, seriesId, movieId, movieName, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMovie(String id, String seriesId, String movieId, String title, String imageUrl, String description) {
        Movie movie = new Movie(seriesId, movieId, title, imageUrl, description);
        Call<Void> call = crudService.updateMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated a movie");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update a movie");
            }
        });
    }
}
