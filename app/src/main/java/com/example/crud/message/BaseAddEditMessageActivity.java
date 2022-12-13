package com.example.crud.message;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.base.BaseActivity;
import com.example.crud.internet.CrudApi;
import com.example.crud.internet.CrudService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMessageActivity extends BaseActivity {

    protected EditText nameTxt;
    protected EditText phoneNumberTxt;
    protected EditText messageTxt;
    protected CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        log("onCreate");
        findViews();
        setupCrudApi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }

    // Rename method
    private void setupCrudApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void findViews() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }
}