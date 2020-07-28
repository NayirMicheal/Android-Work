package com.example.nayir.moviesapp.MoviesMVP;

import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public interface MoviesView {
    void UpdateRecyclerView(ArrayList<movie> movies);

    void cantRetrieveFromOnline();

    void showToast(String message);

    interface movieview {
        void cantRetrivefilm();

        void updateDetailView(movie film);
    }
}
