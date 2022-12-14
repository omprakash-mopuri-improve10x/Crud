package com.example.crud.dashboard;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;

// Todo: Rename the class DashboardViewHolder to DashboardItemViewHolder
public class DashboardViewHolder extends RecyclerView.ViewHolder {

    ImageView dashboardImg;
    TextView titleTxt;
    // Todo: Remove "dashboardLayout"
    LinearLayout dashboardLayout;

    public DashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        dashboardImg = itemView.findViewById(R.id.dashboard_img);
        titleTxt = itemView.findViewById(R.id.title_txt);
        dashboardLayout = itemView.findViewById(R.id.dashboard_layout);
    }
}
