package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesListActivity extends AppCompatActivity {

    public RecyclerView seriesListRv;
    public ArrayList<Series> seriesList = new ArrayList<>();
    public SeriesListAdapter seriesListAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);
        getSupportActionBar().setTitle("Series");
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
            Intent intent = new Intent(this, AddEditSeriesActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchSeriesList() {
        showProgressBar();
        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesService();
        Call<List<Series>> call = seriesListService.fetchSeriesList();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideProgressBar();
                List<Series> seriesList1 = response.body();
                seriesListAdapter.setData(seriesList1);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(SeriesListActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteSeries(String id) {
        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesService();
        Call<Void> call = seriesListService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesListActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchSeriesList();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesListActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editSeries(Series series) {
        Intent intent = new Intent(this, AddEditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }

    public void setupSeriesListRv() {
        seriesListRv = findViewById(R.id.series_list_rv);
        seriesListRv.setLayoutManager(new LinearLayoutManager(this));
        seriesListAdapter = new SeriesListAdapter();
        seriesListAdapter.setData(seriesList);
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
        seriesListRv.setAdapter(seriesListAdapter);
    }

    public void showProgressBar() {
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }
}