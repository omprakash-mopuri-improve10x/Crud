package com.example.crud.series;

import com.example.crud.series.Series;

public interface SeriesOnItemActionListener {

    void onDelete(String id);

    void onEdit(Series series);
}
