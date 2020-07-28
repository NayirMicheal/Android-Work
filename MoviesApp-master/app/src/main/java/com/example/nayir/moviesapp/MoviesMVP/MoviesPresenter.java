package com.example.nayir.moviesapp.MoviesMVP;

import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public interface MoviesPresenter {
    void MoviesFeteched(ArrayList<movie> result);

    void MoviesUnFetched();

    void SendToast(String message);

    void fetchMovie(movie film);

    void unFetchMovie();

    void getMovies();

    void getFilm(int RealDbPosition);
}
