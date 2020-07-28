package com.example.nayir.moviesapp.RetrofitUsage;

import android.content.Context;

import com.example.nayir.moviesapp.MyApplication;
import com.example.nayir.moviesapp.models.review;
import com.example.nayir.moviesapp.models.reviewsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nayir on 5/4/2017.
 */

public class callRetrofitReviews {
    static boolean callCreatedReviews = false;
    static callRetrofitReviews RetrofitReviews;
    static Context con;
    ArrayList<review> reviews;

    private callRetrofitReviews(Context context) {
        con = context;
    }

    public static callRetrofitReviews getInstance() {
        if (!callCreatedReviews) {
            con = MyApplication.getContext();
        }
        RetrofitReviews = new callRetrofitReviews(con);
        callCreatedReviews = true;
        return RetrofitReviews;
    }

    public void retrofitCall(int filmId, final NetworkResponse<ArrayList<review>> reviewsResponse) {
        RetroClient retro = RetroClient.getInstanceRetrofit(con);
        ApiEndpoints.ApiReviews api = retro.getApiReviews();
        Call<reviewsResponse> call = api.getMyJSON(String.valueOf(filmId));
        call.enqueue(new Callback<reviewsResponse>() {
            @Override
            public void onResponse(Call<reviewsResponse> call, Response<reviewsResponse> response) {
                if (response.isSuccessful()) {
                    reviews = response.body().getResult();
                    reviewsResponse.onSucess(reviews);
                }
            }

            @Override
            public void onFailure(Call<reviewsResponse> call, Throwable t) {
                reviewsResponse.onFailure(t.getMessage());
            }

        });
    }
}
