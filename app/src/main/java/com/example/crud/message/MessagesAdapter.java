package com.example.crud.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    public List<Message> messages;
    public OnItemActionListener onItemActionListener;

    public void setData(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(view);
        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.nameTxt.setText(message.name);
        holder.phoneNumberTxt.setText(message.phoneNumber);
        holder.messageTxt.setText(message.message);
        holder.deleteIb.setOnClickListener(view -> {
            onItemActionListener.onDelete(message.id);
        });
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onEdit(message);
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}