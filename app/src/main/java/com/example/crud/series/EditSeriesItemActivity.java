package com.example.crud.series;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.crud.Constants;
import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    private SeriesItem seriesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Series");
        seriesItem = (SeriesItem) getIntent().getSerializableExtra(Constants.KEY_SERIES_ITEM);
        showData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String seriesId = binding.seriesIdTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            String seriesName = binding.seriesNameTxt.getText().toString();
            updateSeries(seriesItem.id, seriesId, seriesName, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateSeries(String id, String seriesId, String title, String imageUrl) {
        SeriesItem seriesItem = new SeriesItem(seriesId, title, imageUrl);
        Call<Void> call = crudService.updateSeriesItem(id, seriesItem);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated a series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update a series");
            }
        });
    }

    private void showData() {
        binding.seriesIdTxt.setText(seriesItem.seriesId);
        binding.seriesNameTxt.setText(seriesItem.title);
        binding.imageUrlTxt.setText(seriesItem.imageUrl);
    }
}
