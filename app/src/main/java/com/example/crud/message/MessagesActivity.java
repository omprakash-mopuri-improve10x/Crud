package com.example.crud.message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.crud.Constants;
import com.example.crud.R;
import com.example.crud.internet.CrudApi;
import com.example.crud.internet.CrudService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends AppCompatActivity {

    private RecyclerView messagesRv;
    private ArrayList<Message> messageList = new ArrayList<>();
    private MessagesAdapter messagesAdapter;
    private ProgressBar progressBar;
    private CrudService crudService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        Log.i("MessagesActivity", "onCreate");
        getSupportActionBar().setTitle("Messages");
        progressBar = findViewById(R.id.progress_bar);
        setupCrudApi();
        setupMessagesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MessagesActivity", "onResume");
        fetchMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddEditMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchMessages() {
        showProgressBar();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideProgressBar();
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideProgressBar();
                showMessage("Failed to load data");
            }
        });
    }

    private void deleteMessage(String id) {
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showMessage("Successfully deleted");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showMessage("Failed to delete message");
            }
        });
    }

    private void editMessage(Message message) {
        Intent intent = new Intent(this, AddEditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        startActivity(intent);
    }

    private void setupMessagesRv() {
        messagesRv = findViewById(R.id.messages_rv);
        messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messageList);
        messagesAdapter.setOnItemClickListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMessage(id);
            }

            @Override
            public void onEdit(Message message) {
                editMessage(message);
            }
        });
        messagesRv.setAdapter(messagesAdapter);
    }

    private void setupCrudApi() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}