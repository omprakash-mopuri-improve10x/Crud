package com.example.crud.template;

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

public class TemplatesActivity extends AppCompatActivity {

    private RecyclerView templatesRv;
    private ArrayList<Template> templateList = new ArrayList<>();
    private TemplatesAdapter templatesAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);
        getSupportActionBar().setTitle("Templates");
        setupTemplatesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTemplates();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.templates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void fetchTemplates() {
        showProgressBar();
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplateService();
        Call<List<Template>> call = templatesService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideProgressBar();
                List<Template> templates = response.body();
                templatesAdapter.setData(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(TemplatesActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteTemplate(String id) {
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplateService();
        Call<Void> call = templatesService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(TemplatesActivity.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(TemplatesActivity.this, "Failed to delete a template", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editTemplate(Template template) {
        Intent intent = new Intent(this, AddEditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, template);
        startActivity(intent);
    }

    public void setupTemplatesRv() {
        templatesRv = findViewById(R.id.templates_rv);
        templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setData(templateList);
        templatesAdapter.setTemplateOnItemActionListener(new TemplateOnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
            }

            @Override
            public void onEdit(Template template) {
                editTemplate(template);
            }
        });
        templatesRv.setAdapter(templatesAdapter);
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