package com.example.crud.series;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

// Todo: Rename the SeriesViewHolder to SeriesItemViewHolder
public class SeriesViewHolder extends RecyclerView.ViewHolder {

    ImageView seriesImg;
    TextView titleTxt;
    ImageButton deleteIb;

    public SeriesViewHolder(@NonNull View itemView) {
        super(itemView);
        seriesImg = itemView.findViewById(R.id.series_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        deleteIb = itemView.findViewById(R.id.delete_ib);
    }
}
