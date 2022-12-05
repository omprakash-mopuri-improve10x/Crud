package com.example.crud;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView movieImageImg;
    public TextView titleTxt;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        movieImageImg = itemView.findViewById(R.id.movie_image_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
    }
}
