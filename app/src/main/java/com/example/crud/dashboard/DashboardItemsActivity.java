package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardItemsActivity extends BaseActivity {

    private RecyclerView dashboardItemsRv;
    private ArrayList<DashboardItem> dashboardItems;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        findViews();
        setupData();
        setupDashboardItemsAdapter();
        setupDashboardItemsRv();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();
        DashboardItem messages = new DashboardItem("Messages", "https://i.blogs.es/5a3917/hero/450_1000.webp");
        dashboardItems.add(messages);
        DashboardItem templates = new DashboardItem("Templates", "https://associationsnow.com/wp-content/uploads/2018/08/GettyImages-625873026.jpg");
        dashboardItems.add(templates);
        DashboardItem series = new DashboardItem("Series", "https://earnthis.net/wp-content/uploads/2014/02/hp-posters.jpg");
        dashboardItems.add(series);
        DashboardItem movies = new DashboardItem("Movies", "https://static.wikia.nocookie.net/ultimatepopculture/images/b/b4/At-the-movies.jpg/revision/latest?cb=20191012032527");
        dashboardItems.add(movies);
    }

    private void setupDashboardItemsAdapter() {
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
    }

    private void setupDashboardItemsRv() {
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }

    private void findViews() {
        dashboardItemsRv = findViewById(R.id.dashboard_items_rv);
    }
}