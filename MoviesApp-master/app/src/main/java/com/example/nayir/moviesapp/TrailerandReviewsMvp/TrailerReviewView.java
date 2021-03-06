package com.example.nayir.moviesapp.TrailerandReviewsMvp;

import com.example.nayir.moviesapp.models.review;
import com.example.nayir.moviesapp.models.trailers;

import java.util.ArrayList;

/**
 * Created by nayir on 5/7/2017.
 */

public interface TrailerReviewView {
    void updateTrailers(ArrayList<trailers> trailerses);

    void updateReviews(ArrayList<review> reviews);

    void sendToast(String message);

}
