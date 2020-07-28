package com.example.nayir.moviesapp.MoviesMVP;

import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public class PresenterImpl implements MoviesPresenter {
    MoviesView View;
    MoviesView.movieview viewmovie;
    MoviesInteractor interactor;

    public PresenterImpl(MoviesView view) {
        interactor = new InteractorImpl(this);
        this.View = view;
    }

    public PresenterImpl(MoviesView.movieview ViewMovie) {
        interactor = new InteractorImpl(this);
        this.viewmovie = ViewMovie;
    }

    @Override
    public void MoviesFeteched(ArrayList<movie> result) {
        View.UpdateRecyclerView(result);
    }

    @Override
    public void MoviesUnFetched() {
        View.cantRetrieveFromOnline();
    }

    @Override
    public void SendToast(String message) {
        View.showToast(message);
    }

    @Override
    public void fetchMovie(movie film) {
        viewmovie.updateDetailView(film);
    }

    @Override
    public void unFetchMovie() {
        viewmovie.cantRetrivefilm();
    }

    @Override
    public void getMovies() {
        interactor.getMoviesOnLine();
    }

    @Override
    public void getFilm(int RealDbPosition) {
        interactor.getfilm(RealDbPosition);
    }


}
