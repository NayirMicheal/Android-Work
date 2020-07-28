package com.example.nayir.moviesapp.TrailerandReviewsMvp;

import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.RetrofitUsage.NetworkResponse;
import com.example.nayir.moviesapp.RetrofitUsage.callRetrofitReviews;
import com.example.nayir.moviesapp.RetrofitUsage.callRetrofitTrailers;
import com.example.nayir.moviesapp.models.review;
import com.example.nayir.moviesapp.models.trailers;

import java.util.ArrayList;

import static com.example.nayir.moviesapp.MainActivity.context;

/**
 * Created by nayir on 5/7/2017.
 */

public class TRInrteractorImpl implements TrailerReviewData {
    TrailerReviewPresenter Presenter;

    public TRInrteractorImpl(TrailerReviewPresenter presenter) {
        this.Presenter = presenter;
    }

    @Override
    public void getReviews(int filmId) {
        callRetrofitReviews retrofitReviews = callRetrofitReviews.getInstance();
        retrofitReviews.retrofitCall(filmId, new NetworkResponse<ArrayList<review>>() {
            @Override
            public void onSucess(ArrayList<review> result) {
                Presenter.fetchReviews(result);
            }

            @Override
            public void onFailure(String message) {
                if (message.contains(context.getString(R.string.match_string)))
                    Presenter.cantFetch(context.getString(R.string.Chk_Con));
                else
                    Presenter.cantFetch(context.getString(R.string.Server_Error));
            }
        });
    }

    @Override
    public void getTrailer(int filmId) {
        callRetrofitTrailers retroTrailers = callRetrofitTrailers.getInstance();
        retroTrailers.retrofitCall(filmId, new NetworkResponse<ArrayList<trailers>>() {
            @Override
            public void onSucess(ArrayList<trailers> result) {
                Presenter.fetchTrailers(result);
            }

            @Override
            public void onFailure(String message) {
                Presenter.cantFetch(message);
            }
        });

    }
}
