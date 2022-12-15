package com.example.crud.series;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesItemsAdapter extends RecyclerView.Adapter<SeriesItemViewHolder> {

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
    public SeriesItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.series_item, parent, false);
        SeriesItemViewHolder seriesItemViewHolder = new SeriesItemViewHolder(view);
        return seriesItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesItemViewHolder holder, int position) {
        SeriesItem seriesItem = seriesItems.get(position);
        holder.titleTxt.setText(seriesItem.title);
        if (seriesItem.imageUrl != null && seriesItem.imageUrl.isEmpty() == false) {
            Picasso.get().load(seriesItem.imageUrl).into(holder.seriesImg);
        }
        holder.deleteIb.setOnClickListener(view -> {
            seriesOnItemActionListener.onDelete(seriesItem.id);
        });
        holder.itemView.setOnClickListener(view -> {
            seriesOnItemActionListener.onEdit(seriesItem);
        });
    }

    @Override
    public int getItemCount() {
        return seriesItems.size();
    }
}
