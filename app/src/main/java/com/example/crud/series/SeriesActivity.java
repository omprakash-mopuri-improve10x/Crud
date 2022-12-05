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

import com.example.crud.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesActivity extends AppCompatActivity {

    public RecyclerView seriesRv;
    public ArrayList<Series> seriesList = new ArrayList<>();
    public SeriesAdapter seriesAdapter;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        getSupportActionBar().setTitle("Series");
        setupSeriesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
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

    public void fetchData() {
        showProgressBar();
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<List<Series>> call = seriesService.fetchSerieses();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideProgressBar();
                List<Series> serieses = response.body();
                seriesAdapter.setData(serieses);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(SeriesActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteSeries(String id) {
        SeriesApi seriesApi = new SeriesApi();
        SeriesService seriesService = seriesApi.createSeriesService();
        Call<Void> call = seriesService.deleteSeries(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(SeriesActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchData();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SeriesActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editSeries(Series series) {
        Intent intent = new Intent(this, AddEditSeriesActivity.class);
        intent.putExtra("series", series);
        startActivity(intent);
    }

    public void setupSeriesRv() {
        seriesRv = findViewById(R.id.series_rv);
        seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesAdapter = new SeriesAdapter();
        seriesAdapter.setData(seriesList);
        seriesAdapter.setSeriesOnItemActionListener(new SeriesOnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeries(id);
            }

            @Override
            public void onEdit(Series series) {
                editSeries(series);
            }
        });
        seriesRv.setAdapter(seriesAdapter);
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