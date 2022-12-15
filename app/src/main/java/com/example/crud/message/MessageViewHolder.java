package com.example.crud.message;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    TextView phoneNumberTxt;
    TextView messageTextTxt;
    ImageButton deleteIb;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.name_txt);
        phoneNumberTxt = itemView.findViewById(R.id.phone_number_txt);
        messageTextTxt = itemView.findViewById(R.id.message_text_txt);
        deleteIb = itemView.findViewById(R.id.delete_ib);
    }
}
