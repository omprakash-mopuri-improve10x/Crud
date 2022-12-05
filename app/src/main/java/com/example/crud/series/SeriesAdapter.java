package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    public List<Series> seriesArrayList;
    public SeriesOnItemActionListener seriesOnItemActionListener;

    public void setSeriesOnItemActionListener(SeriesOnItemActionListener seriesOnItemActionListener) {
        this.seriesOnItemActionListener = seriesOnItemActionListener;
    }

    public void setData(List<Series> seriesArrayList) {
        this.seriesArrayList = seriesArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series series = seriesArrayList.get(position);
        holder.titleTxt.setText(series.title);
        if (series.imageUrl != null && series.imageUrl.isEmpty() == false) {
            Picasso.get().load(series.imageUrl).into(holder.imageImg);
        }
        holder.deleteIb.setOnClickListener(view -> {
            seriesOnItemActionListener.onDelete(series.id);
        });
        holder.itemView.setOnClickListener(view -> {
            seriesOnItemActionListener.onEdit(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesArrayList.size();
    }
}
