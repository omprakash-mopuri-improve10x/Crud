package com.example.crud.movies;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.base.BaseActivity;
import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.internet.CrudApi;
import com.example.crud.internet.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private RecyclerView moviesRv;
    private ArrayList<Movie> movieList = new ArrayList<>();
    private MoviesAdapter moviesAdapter;
    private CrudService crudService;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        log("onCreate");
        progressBar = findViewById(R.id.progress_bar);
        setupCrudApi();
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("onResume");
        fetchMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchMovies() {
        showProgressBar();
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hideProgressBar();
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load data");
            }
        });
    }

    private void deleteMovie(String id) {
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted a movie");
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete a movie");
            }
        });
    }

    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }

    private void setupMoviesRv() {
        moviesRv = findViewById(R.id.movies_rv);
        moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movieList);
        moviesAdapter.setMovieOnItemActionListener(new MovieOnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovie(id);
            }

            @Override
            public void onEdit(Movie movie) {
                editMovie(movie);
            }
        });
        moviesRv.setAdapter(moviesAdapter);
    }

    private void setupCrudApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}