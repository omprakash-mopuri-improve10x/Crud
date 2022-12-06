package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.crud.R;
import com.example.crud.series.Series;
import com.example.crud.series.SeriesListApi;
import com.example.crud.series.SeriesListService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditMovieActivity extends AppCompatActivity {

    public Spinner seriesSp;
    public CustomSeriesListAdapter customSeriesListAdapter;
    public ArrayList<Series> seriesList = new ArrayList<>();
    public EditText movieIdTxt;
    public EditText movieNameTxt;
    public EditText imageUrlTxt;
    public EditText descriptionTxt;
    public Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        findViews();
        fetchSeriesList();
        setupSeriesListSp();
        if (getIntent().hasExtra("movie")) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) getIntent().getSerializableExtra("movie");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Movie");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String movieId = movieIdTxt.getText().toString();
            Series series = (Series) seriesSp.getSelectedItem();
            String seriesId = series.seriesId;
            String imageUrl = imageUrlTxt.getText().toString();
            String movieName = movieNameTxt.getText().toString();
            String description = descriptionTxt.getText().toString();
            if (movie == null) {
                addMovie(movieId, seriesId, imageUrl, movieName, description);
            } else {
                updateMovie(movie.id, movieId, seriesId, imageUrl, movieName, description);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchSeriesList() {
        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesService();
        Call<List<Series>> call = seriesListService.fetchSeriesList();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> seriesList1 = response.body();
                customSeriesListAdapter.addAll(seriesList1);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addMovie(String movieId, String seriesId, String imageUrl, String title, String description) {
        Movie movie = new Movie(movieId, seriesId, imageUrl, title, description);
        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Movie> call = moviesService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Toast.makeText(AddEditMovieActivity.this, "Successfully added a movie", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to add a movie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateMovie(String id, String movieId, String seriesId, String imageUrl, String title, String description) {
        Movie movie = new Movie(movieId, seriesId, imageUrl, title, description);
        MoviesApi moviesApi = new MoviesApi();
        MoviesService moviesService = moviesApi.createMoviesService();
        Call<Void> call = moviesService.editMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditMovieActivity.this, "Successfully updated a movie", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to update a movie", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setupSeriesListSp() {
        seriesSp = findViewById(R.id.series_sp);
        customSeriesListAdapter = new CustomSeriesListAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesListAdapter);
    }

    public void showData() {
        movieIdTxt.setText(movie.movieId);
        movieNameTxt.setText(movie.title);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesListAdapter.getCount(); i++) {
            Series series = customSeriesListAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                seriesSp.setSelection(i);
            }
        }
    }

    public void findViews() {
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }
}