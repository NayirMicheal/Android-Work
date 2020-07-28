package com.example.nayir.moviesapp.RetrofitUsage;

import android.content.Context;
import android.widget.Toast;

import com.example.nayir.moviesapp.MyApplication;
import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.models.MoviesResponse;
import com.example.nayir.moviesapp.models.filmsResponse;
import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nayir on 5/4/2017.
 */

public class callRetrofitMovies {
    static boolean callCreatedMovies = false;
    static callRetrofitMovies callRetrofitForMovies;
    static Context con;
    ArrayList<filmsResponse> fResponse;
    ArrayList<movie> movies;

    private callRetrofitMovies(Context context) {
        con = context;
    }

    public static callRetrofitMovies getInstance() {
        if (!callCreatedMovies) {
            con = MyApplication.getContext();
            callRetrofitForMovies = new callRetrofitMovies(con);
        }
        callCreatedMovies = true;
        return callRetrofitForMovies;
    }

    public void retrofitCall(final NetworkResponse<ArrayList<movie>> Moviesresponse) {
        movies = new ArrayList<>();
        RetroClient retro = RetroClient.getInstanceRetrofit(con);
        ApiEndpoints.ApiService api = retro.getApiService();
        Call<MoviesResponse> call = api.getMyJSON();
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    fResponse = response.body().getFilmsResponses();
                    for (filmsResponse res : fResponse) {
                        movie film = new movie(res.getTitle(), res.getReleaseDate(), res.getOverView(), res.getPosterPath(), res.getId());
                        movies.add(film);
                    }
                    Moviesresponse.onSucess(movies);
                } else {
                    Toast.makeText(con, R.string.Not_success, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Moviesresponse.onFailure(t.getMessage());
            }
        });
    }
}
