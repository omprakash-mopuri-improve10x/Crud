package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {
    // Todo: Add the progress bar in DashboardItemsActivity

    private RecyclerView dashboardItemsRv;
    private ArrayList<DashboardItem> dashboardItems;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRv();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        DashboardItem dashboard = new DashboardItem("https://i.blogs.es/5a3917/hero/450_1000.webp", "Messages");
        dashboardItems.add(dashboard);
        DashboardItem dashboard1 = new DashboardItem("https://associationsnow.com/wp-content/uploads/2018/08/GettyImages-625873026.jpg", "Templates");
        dashboardItems.add(dashboard1);
        DashboardItem dashboard2 = new DashboardItem("https://earnthis.net/wp-content/uploads/2014/02/hp-posters.jpg", "Series");
        dashboardItems.add(dashboard2);
        DashboardItem dashboard3 = new DashboardItem("https://static.wikia.nocookie.net/ultimatepopculture/images/b/b4/At-the-movies.jpg/revision/latest?cb=20191012032527", "Movies");
        dashboardItems.add(dashboard3);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }

    private void setupDashboardItemsRv() {
        dashboardItemsRv = findViewById(R.id.dashboard_items_rv);
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }
}