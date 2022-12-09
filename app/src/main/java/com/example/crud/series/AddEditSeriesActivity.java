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
import com.example.crud.internet.CrudApi;
import com.example.crud.internet.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditSeriesActivity extends AppCompatActivity {

    private EditText seriesIdTxt;
    private EditText seriesNameTxt;
    private EditText imageUrlTxt;
    private Series series;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_series);
        Log.i("AddEditSeriesActivity", "onCreate");
        findViews();
        setupCrudApi();
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
        Call<Series> call = crudService.createSeries(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showMessage("Successfully added a series");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showMessage("Failed to add a series");
            }
        });
    }

    private void updateSeries(String id, String seriesId, String imageUrl, String title) {
        Series series = new Series(seriesId, imageUrl, title);
        Call<Void> call = crudService.EditSeries(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showMessage("Successfully updated a series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showMessage("Failed to update series");
            }
        });
    }

    private void setupCrudApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
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

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}