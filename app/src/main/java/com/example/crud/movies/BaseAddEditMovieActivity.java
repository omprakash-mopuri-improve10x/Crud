package com.example.crud.movies;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.internet.CrudApi;
import com.example.crud.internet.CrudService;
import com.example.crud.series.Series;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected Spinner seriesSp;
    protected CustomSeriesListAdapter customSeriesListAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    protected EditText movieIdTxt;
    protected EditText movieNameTxt;
    protected EditText imageUrlTxt;
    protected EditText descriptionTxt;
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        findViews();
        fetchSeriesList();
        setupSeriesListSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    // Todo: Rename the method fetchSeriesList() to fetchSeriesItems()
    private void fetchSeriesList() {
        Call<List<Series>> call = crudService.fetchSeriesList();
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
                showToast("Failed to load data");
            }
        });
    }

    // Todo: Rename the method fetchSeriesListSp() to fetchSeriesItemsSp()
    private void setupSeriesListSp() {
        customSeriesListAdapter = new CustomSeriesListAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesListAdapter);
    }

    protected void showData() {
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

    private void findViews() {
        seriesSp = findViewById(R.id.series_sp);
        movieIdTxt = findViewById(R.id.movie_id_txt);
        movieNameTxt = findViewById(R.id.movie_name_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
        descriptionTxt = findViewById(R.id.description_txt);
    }
}