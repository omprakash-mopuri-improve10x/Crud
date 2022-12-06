package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        getSupportActionBar().setTitle("Add Movie");
       // setupData();
        fetchSeriesList();
        setupSeriesListSp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AddEditMovieActivity.this, "Successfully Completed", Toast.LENGTH_SHORT).show();
                List<Series> seriesList1 = response.body();
                customSeriesListAdapter.addAll(seriesList1);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                Toast.makeText(AddEditMovieActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

     public void setupData() {
        seriesList = new ArrayList<>();
        Series series = new Series("1", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxgI5VTfmjiyUvz3N0CHzdHtroa1lWbrWFBA&usqp=CAU", "AlluArjun");
        seriesList.add(series);
        seriesList.add(series);
    }

    public void setupSeriesListSp() {
        seriesSp = findViewById(R.id.series_sp);
        customSeriesListAdapter = new CustomSeriesListAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        seriesSp.setAdapter(customSeriesListAdapter);
    }
}