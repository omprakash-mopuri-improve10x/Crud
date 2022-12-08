package com.example.crud.series;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {

    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText imageUrlTxt;
    private Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        Log.i("AddEditSeriesActivity", "onCreate");
        findViews();
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit series");
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        } else {
            getSupportActionBar().setTitle("Add Series");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String seriesId = seriesIdTxt.getText().toString();
            String imageUrl = imageUrlTxt.getText().toString();
            String seriesName = seriesNameTxt.getText().toString();
            if (series == null) {
                addSeries(seriesId, imageUrl, seriesName);
            } else {
               updateSeries(series.id, seriesId, imageUrl, seriesName);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeries(String seriesId, String imageUrl, String seriesName) {
        Series series = new Series(seriesId, imageUrl, seriesName);
        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesService();
        Call<Series> call = seriesListService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully added a series", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed to add a series", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateSeries(String id, String seriesId, String imageUrl, String title) {
        Series series = new Series(seriesId, imageUrl, title);
        SeriesListApi seriesListApi = new SeriesListApi();
        SeriesListService seriesListService = seriesListApi.createSeriesService();
        Call<Void> call = seriesListService.EditSeries(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditSeriesActivity.this, "Successfully updated a series", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditSeriesActivity.this, "Failed to update series", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showData() {
        seriesIdTxt.setText(series.seriesId);
        seriesNameTxt.setText(series.title);
        imageUrlTxt.setText(series.imageUrl);
    }

    private void findViews() {
        seriesIdTxt = findViewById(R.id.series_id_txt);
        seriesNameTxt = findViewById(R.id.series_name_txt);
        imageUrlTxt = findViewById(R.id.image_url_txt);
    }
}