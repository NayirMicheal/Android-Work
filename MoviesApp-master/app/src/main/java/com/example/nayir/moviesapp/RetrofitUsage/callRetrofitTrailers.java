package com.example.nayir.moviesapp.RetrofitUsage;

import android.content.Context;

import com.example.nayir.moviesapp.MyApplication;
import com.example.nayir.moviesapp.models.trailerResponse;
import com.example.nayir.moviesapp.models.trailers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayir on 5/3/2017.
 */

public class callRetrofitTrailers {
    static boolean callTrailerCreated = false;
    static callRetrofitTrailers callRetrofitForTrailers;
    static Context con;

    private callRetrofitTrailers(Context context) {
        con = context;
    }

    public static callRetrofitTrailers getInstance() {
        if (!callTrailerCreated) {
            con = MyApplication.getContext();
            callRetrofitForTrailers = new callRetrofitTrailers(con);
        }
        callTrailerCreated = true;
        return callRetrofitForTrailers;
    }

    public void retrofitCall(int filmID, final NetworkResponse<ArrayList<trailers>> trailersResponse) {
        RetroClient retro = RetroClient.getInstanceRetrofit(con);
        ApiEndpoints.ApiTrailers api = retro.getApiTrailers();
        final String filmid = String.valueOf(filmID);
        Call<trailerResponse> call = api.getMyJSON(filmid);
        call.enqueue(new Callback<trailerResponse>() {
            @Override
            public void onResponse(Call<trailerResponse> call, Response<trailerResponse> response) {
                if (response.isSuccessful()) {
                    trailersResponse.onSucess(response.body().getTrailers());
                }
            }

            @Override
            public void onFailure(Call<trailerResponse> call, Throwable t) {
                trailersResponse.onFailure(t.getMessage());
            }

        });
    }
}
