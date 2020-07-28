package com.example.nayir.moviesapp.TrailerandReviewsMvp;

import com.example.nayir.moviesapp.models.review;
import com.example.nayir.moviesapp.models.trailers;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public interface TrailerReviewPresenter {
    void fetchTrailers(ArrayList<trailers> trailerses);

    void fetchReviews(ArrayList<review> reviews);

    void cantFetch(String message);

    void getReviews(int FILMID);

    void getTrailers(int FILMID);

}
