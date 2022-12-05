package com.example.crud.template;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    public TextView messageTxt;
    public ImageButton delete_ib;

    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        messageTxt = itemView.findViewById(R.id.message_txt);
        delete_ib = itemView.findViewById(R.id.delete_ib);
    }
}
