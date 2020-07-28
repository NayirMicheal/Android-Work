package com.example.nayir.moviesapp.RetrofitUsage;

import android.content.Context;

import com.example.nayir.moviesapp.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nayir on 4/24/2017.
 */

public class RetroClient {
    static boolean created = false;
    static RetroClient client;
    private static String ROOT_URL;
    Context context;

    private RetroClient(Context context) {
        this.context = context;
        ROOT_URL = context.getString(R.string.MoviesApiUrl);
    }

    public static RetroClient getInstanceRetrofit(Context con) {

        if (!created)
            client = new RetroClient(con);
        created = true;
        return client;
    }

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ApiEndpoints.ApiService getApiService() {
        return getRetrofitInstance().create(ApiEndpoints.ApiService.class);
    }

    public ApiEndpoints.ApiReviews getApiReviews() {
        return getRetrofitInstance().create(ApiEndpoints.ApiReviews.class);
    }

    public ApiEndpoints.ApiTrailers getApiTrailers() {
        return getRetrofitInstance().create(ApiEndpoints.ApiTrailers.class);
    }
}
