package com.example.crud;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImg;
    public TextView titleTxt;
    public ImageButton deleteIb;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImg = itemView.findViewById(R.id.movie_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        deleteIb = itemView.findViewById(R.id.delete_ib);
    }
}
