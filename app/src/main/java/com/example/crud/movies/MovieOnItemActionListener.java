package com.example.crud.movies;

import com.example.crud.movies.Movie;

public interface MovieOnItemActionListener {

    void onDelete(String id);

    void onEdit(Movie movie);
}
