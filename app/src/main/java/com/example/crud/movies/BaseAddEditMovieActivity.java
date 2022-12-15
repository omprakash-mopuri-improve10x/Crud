package com.example.crud.movies;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.series.SeriesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected Spinner seriesSp;
    // Todo: remove protected give private, check all adapter modifiers
    protected CustomSeriesItemsAdapter customSeriesItemsAdapter;
    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
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
        fetchSeriesItems();
        setupSeriesItemsSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeriesItems() {
        Call<List<SeriesItem>> call = crudService.fetchSeriesList();
        call.enqueue(new Callback<List<SeriesItem>>() {
            @Override
            public void onResponse(Call<List<SeriesItem>> call, Response<List<SeriesItem>> response) {
                List<SeriesItem> seriesList1 = response.body();
                customSeriesItemsAdapter.addAll(seriesList1);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<SeriesItem>> call, Throwable t) {
                showToast("Failed to load data");
            }
        });
    }

    private void setupSeriesItemsSp() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
        seriesSp.setAdapter(customSeriesItemsAdapter);
    }

    protected void showData() {
        movieIdTxt.setText(movie.movieId);
        movieNameTxt.setText(movie.title);
        imageUrlTxt.setText(movie.imageUrl);
        descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            SeriesItem series = customSeriesItemsAdapter.getItem(i);
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