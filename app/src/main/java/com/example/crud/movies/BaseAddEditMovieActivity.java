package com.example.crud.movies;

import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.databinding.ActivityBaseAddEditMovieBinding;
import com.example.crud.series.SeriesItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected ActivityBaseAddEditMovieBinding binding;
    private CustomSeriesItemsAdapter customSeriesItemsAdapter;
    private ArrayList<SeriesItem> seriesItems = new ArrayList<>();
    protected Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBaseAddEditMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupCustomSeriesItemsAdapter();
        fetchSeriesItems();
        setupSeriesItemsSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.base_add_edit_movie_menu, menu);
        return true;
    }

    private void fetchSeriesItems() {
        Call<List<SeriesItem>> call = crudService.fetchSeriesItems();
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

    private void setupCustomSeriesItemsAdapter() {
        customSeriesItemsAdapter = new CustomSeriesItemsAdapter(this, android.R.layout.simple_list_item_1, seriesItems);
    }

    private void setupSeriesItemsSp() {
        binding.seriesSp.setAdapter(customSeriesItemsAdapter);
    }

    protected void showData() {
        binding.setMovie(movie);
        for (int i = 0; i < customSeriesItemsAdapter.getCount(); i++) {
            SeriesItem series = customSeriesItemsAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                binding.seriesSp.setSelection(i);
            }
        }
    }
}