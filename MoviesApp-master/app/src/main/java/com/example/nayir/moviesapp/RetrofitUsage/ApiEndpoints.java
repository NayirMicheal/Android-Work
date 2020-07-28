package com.example.nayir.moviesapp.RetrofitUsage;

import com.example.nayir.moviesapp.models.MoviesResponse;
import com.example.nayir.moviesapp.models.reviewsResponse;
import com.example.nayir.moviesapp.models.trailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nayir on 5/4/2017.
 */

public class ApiEndpoints {

    public interface ApiReviews {
        @GET ( "3/movie/{id}/reviews?api_key=7505525195eef4876a20f55236b125ee" )
        Call<reviewsResponse> getMyJSON(@Path ( "id" ) String id);
    }

    public interface ApiService {
        @GET ( "3/list/1?api_key=7505525195eef4876a20f55236b125ee" )
        Call<MoviesResponse> getMyJSON();
    }

    public interface ApiTrailers {

        @GET ( "3/movie/{id}/videos?api_key=7505525195eef4876a20f55236b125ee" )
        Call<trailerResponse> getMyJSON(@Path ( "id" ) String id);
    }
}
