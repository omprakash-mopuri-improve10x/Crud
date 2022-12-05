package com.example.crud.template;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditTemplateActivity extends AppCompatActivity {

    public EditText messageTxt;
    public Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_template);
        findIds();
        if (getIntent().hasExtra("template")) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) getIntent().getSerializableExtra("template");
            showData();
        } else {
            getSupportActionBar().setTitle("Add template");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_template_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String message = messageTxt.getText().toString();
            if (template == null) {
                addTemplate(message);
            } else {
                updateTemplate(template.id, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void addTemplate(String message) {
        Template template = new Template(message);
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplateService();
        Call<Template> call = templatesService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully added a template", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed to add Template", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateTemplate(String id, String message) {
        template = new Template(message);
        TemplatesApi templatesApi = new TemplatesApi();
        TemplatesService templatesService = templatesApi.createTemplateService();
        Call<Void> call = templatesService.updateTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditTemplateActivity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditTemplateActivity.this, "Failed to update", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findIds() {
        messageTxt = findViewById(R.id.message_txt);
    }

    public void showData() {
        messageTxt.setText(template.message);
    }
}