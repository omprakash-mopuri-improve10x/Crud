package com.example.crud.dashboard;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;
import com.example.crud.base.BaseActivity;

import java.util.ArrayList;

public class DashboardActivity extends BaseActivity {

    private RecyclerView dashboardItemsRv;
    private ArrayList<DashboardItem> dashboardItemList;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardRv();
    }

    private void setupData() {
        dashboardItemList = new ArrayList<>();
        DashboardItem dashboard = new DashboardItem("https://i.blogs.es/5a3917/hero/450_1000.webp", "Messages");
        dashboardItemList.add(dashboard);
        DashboardItem dashboard1 = new DashboardItem("https://associationsnow.com/wp-content/uploads/2018/08/GettyImages-625873026.jpg", "Templates");
        dashboardItemList.add(dashboard1);
        DashboardItem dashboard2 = new DashboardItem("https://earnthis.net/wp-content/uploads/2014/02/hp-posters.jpg", "Series");
        dashboardItemList.add(dashboard2);
        DashboardItem dashboard3 = new DashboardItem("https://static.wikia.nocookie.net/ultimatepopculture/images/b/b4/At-the-movies.jpg/revision/latest?cb=20191012032527", "Movies");
        dashboardItemList.add(dashboard3);
    }

    private void setupDashboardRv() {
        dashboardItemsRv = findViewById(R.id.dashboard_items_rv);
        dashboardItemsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItemList);
        dashboardItemsRv.setAdapter(dashboardItemsAdapter);
    }
}