package com.example.crud.dashboard;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.MoviesActivity;
import com.example.crud.series.SeriesListActivity;
import com.example.crud.template.TemplatesActivity;
import com.example.crud.message.MessagesActivity;
import com.example.crud.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardsAdapter extends RecyclerView.Adapter<DashboardViewHolder> {

    public ArrayList<Dashboard> dashboards;

    public void setData(ArrayList<Dashboard> dashboards) {
        this.dashboards = dashboards;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_item, parent, false);
        DashboardViewHolder dashboardViewHolder = new DashboardViewHolder(view);
        return dashboardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, int position) {
        Dashboard dashboard = dashboards.get(position);
        Picasso.get().load(dashboard.imageUrl).into(holder.dashboardImg);
        holder.titleTxt.setText(dashboard.title);
        holder.dashboardLayout.setOnClickListener(view -> {
            if (holder.titleTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.itemView.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.itemView.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Series")) {
                Intent intent = new Intent(holder.itemView.getContext(), SeriesListActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.titleTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.itemView.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboards.size();
    }
}
