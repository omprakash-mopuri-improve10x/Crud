package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

// Todo: Rename The class SeriesListAdapter to SeriesItemsAdapter
public class SeriesListAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    private List<SeriesItem> seriesItems;
    private SeriesOnItemActionListener seriesOnItemActionListener;

    void setSeriesOnItemActionListener(SeriesOnItemActionListener seriesOnItemActionListener) {
        this.seriesOnItemActionListener = seriesOnItemActionListener;
    }

    void setData(List<SeriesItem> seriesItems) {
        this.seriesItems = seriesItems;
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
        SeriesItem series = seriesItems.get(position);
        holder.titleTxt.setText(series.title);
        if (series.imageUrl != null && series.imageUrl.isEmpty() == false) {
            Picasso.get().load(series.imageUrl).into(holder.seriesImg);
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
        return seriesItems.size();
    }
}
