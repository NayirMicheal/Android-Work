package com.example.nayir.moviesapp.TrailerandReviewsMvp;

import com.example.nayir.moviesapp.models.review;
import com.example.nayir.moviesapp.models.trailers;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public class TRPresenterImpl implements TrailerReviewPresenter {
    TrailerReviewView View;
    TrailerReviewData interactor;
    boolean created = false;

    public TRPresenterImpl(TrailerReviewView view) {
        this.View = view;
        if (!created)
            interactor = new TRInrteractorImpl(this);
        created = true;
    }

    @Override
    public void fetchTrailers(ArrayList<trailers> trailerses) {
        View.updateTrailers(trailerses);
    }

    @Override
    public void fetchReviews(ArrayList<review> reviews) {
        View.updateReviews(reviews);
    }

    @Override
    public void cantFetch(String message) {
        View.sendToast(message);
    }

    @Override
    public void getReviews(int FILMID) {
        interactor.getReviews(FILMID);
    }

    @Override
    public void getTrailers(int FILMID) {
        interactor.getTrailer(FILMID);
    }

}
