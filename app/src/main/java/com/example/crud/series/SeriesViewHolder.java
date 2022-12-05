package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageImg;
    public TextView titleTxt;
    public ImageButton deleteIb;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageImg = itemView.findViewById(R.id.image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        deleteIb = itemView.findViewById(R.id.delete_ib);
    }
}
