package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Todo: Rename the class SeriesListActivity to SeriesItemsActivity
public class SeriesListActivity extends BaseActivity {

    private RecyclerView seriesListRv;
    private ArrayList<Series> seriesItems = new ArrayList<>();
    private SeriesListAdapter seriesListAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        getSupportActionBar().setTitle("Series");
        findViews();
        setupSeriesListAdapter();
        setupSeriesListRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchSeriesList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchSeriesList() {
        showProgressBar();
        Call<List<Series>> call = crudService.fetchSeriesList();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideProgressBar();
                List<Series> seriesItems = response.body();
                seriesListAdapter.setData(seriesItems);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load data");
            }
        });
    }

    private void deleteSeries(String id) {
        showProgressBar();
        Call<Void> call = crudService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                hideProgressBar();
                showToast("Successfully deleted");
                fetchSeriesList();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to delete");
            }
        });
    }

    private void editSeries(Series series) {
        Intent intent = new Intent(this, EditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }

    // Todo: Rename the method setupSeriesListAdapter() to setupSeriesItemsAdapter()
    private void setupSeriesListAdapter() {
        seriesListAdapter = new SeriesListAdapter();
        seriesListAdapter.setData(seriesItems);
        seriesListAdapter.setSeriesOnItemActionListener(new SeriesOnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeries(id);
            }

            @Override
            public void onEdit(Series series) {
                editSeries(series);
            }
        });
    }

    // Todo: Rename the method setupSeriesListRv() to setupSeriesItemsRv()
    private void setupSeriesListRv() {
        seriesListRv.setLayoutManager(new LinearLayoutManager(this));
        seriesListRv.setAdapter(seriesListAdapter);
    }

    private void findViews() {
        seriesListRv = findViewById(R.id.series_list_rv);
        progressBar = findViewById(R.id.progress_bar);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}