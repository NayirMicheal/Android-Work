package com.example.nayir.moviesapp.MoviesMVP;

import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public interface MoviesInteractor {
    void getMoviesOnLine();

    boolean getMoviesFromDatabase(ArrayList<movie> result);

    void getfilm(int film);
}
