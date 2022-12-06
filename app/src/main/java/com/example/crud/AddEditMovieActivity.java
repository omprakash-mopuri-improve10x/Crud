package com.example.crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEditMovieActivity extends AppCompatActivity {

    public Spinner seriesSp;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> seriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_movie);
        getSupportActionBar().setTitle("Add Movie");
        setupData();
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

     public void setupData() {
        seriesList = new ArrayList<>();
        seriesList.add("Horror Movies");
        seriesList.add("Horror Movies");
        seriesList.add("Horror Movies");
        seriesList.add("Horror Movies");
        seriesList.add("Horror Movies");
        seriesList.add("Horror Movies");
        seriesList.add("Horror Movies");
    }

    public void setupSeriesListSp() {
        seriesSp = findViewById(R.id.series_sp);
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.series_drop_down, seriesList);
        seriesSp.setAdapter(arrayAdapter);
    }
}