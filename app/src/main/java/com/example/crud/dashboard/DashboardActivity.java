package com.example.crud.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.crud.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView dashboardsRv;
    private ArrayList<Dashboard> dashboardList;
    private DashboardAdapter dashboardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardsRv();
    }

    private void setupData() {
        dashboardList = new ArrayList<>();
        Dashboard dashboard = new Dashboard("https://i.blogs.es/5a3917/hero/450_1000.webp", "Messages");
        dashboardList.add(dashboard);
        Dashboard dashboard1 = new Dashboard("https://associationsnow.com/wp-content/uploads/2018/08/GettyImages-625873026.jpg", "Templates");
        dashboardList.add(dashboard1);
        Dashboard dashboard2 = new Dashboard("https://earnthis.net/wp-content/uploads/2014/02/hp-posters.jpg", "Series");
        dashboardList.add(dashboard2);
        Dashboard dashboard3 = new Dashboard("https://static.wikia.nocookie.net/ultimatepopculture/images/b/b4/At-the-movies.jpg/revision/latest?cb=20191012032527", "Movies");
        dashboardList.add(dashboard3);
    }

    private void setupDashboardsRv() {
        dashboardsRv = findViewById(R.id.dashboards_rv);
        dashboardsRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardAdapter = new DashboardAdapter();
        dashboardAdapter.setData(dashboardList);
        dashboardsRv.setAdapter(dashboardAdapter);
    }
}