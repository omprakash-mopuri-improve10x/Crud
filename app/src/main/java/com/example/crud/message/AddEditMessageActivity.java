package com.example.crud.message;

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

public class AddEditMessageActivity extends AppCompatActivity {

    public EditText nameTxt;
    public EditText phoneNumberTxt;
    public EditText messageTxt;
    public Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_message);
        findViews();
        if (getIntent().hasExtra("message")) {
            getSupportActionBar().setTitle("Edit Message");
            message = (Message) getIntent().getSerializableExtra("message");
            showData();
        } else {
            getSupportActionBar().setTitle("Add Message");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.done) {
            String name = nameTxt.getText().toString();
            String phoneNumber = phoneNumberTxt.getText().toString();
            String message = messageTxt.getText().toString();
            if (this.message == null) {
                addMessage(name, phoneNumber, message);
            } else {
                updateMessage(this.message.id, name, phoneNumber, message);
            }
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void addMessage(String name, String phoneNumber, String messageTxt) {
        Message message = new Message(name, phoneNumber, messageTxt);
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Message> call = messagesService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(AddEditMessageActivity.this, "Successfully added a message", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(AddEditMessageActivity.this, "Failed add message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateMessage(String id, String name, String phoneNumber, String messageTxt) {
        Message message = new Message(name, phoneNumber, messageTxt);
        MessagesApi messagesApi = new MessagesApi();
        MessagesService messagesService = messagesApi.createMessagesService();
        Call<Void> call = messagesService.updateMessage(id, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddEditMessageActivity.this, "Successfully updated ", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEditMessageActivity.this, "Failed to update message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findViews() {
        nameTxt = findViewById(R.id.name_txt);
        phoneNumberTxt = findViewById(R.id.phone_number_txt);
        messageTxt = findViewById(R.id.message_txt);
    }

    public void showData() {
        nameTxt.setText(message.name);
        phoneNumberTxt.setText(message.phoneNumber);
        messageTxt.setText(message.messageText);
    }
}