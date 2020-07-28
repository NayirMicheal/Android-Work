package com.example.nayir.moviesapp.MoviesMVP;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;

import com.example.nayir.moviesapp.MyApplication;
import com.example.nayir.moviesapp.R;
import com.example.nayir.moviesapp.RetrofitUsage.NetworkResponse;
import com.example.nayir.moviesapp.RetrofitUsage.callRetrofitMovies;
import com.example.nayir.moviesapp.data.MovieHelper;
import com.example.nayir.moviesapp.data.movieContract;
import com.example.nayir.moviesapp.data.movieProvider;
import com.example.nayir.moviesapp.models.movie;

import java.util.ArrayList;

import static com.example.nayir.moviesapp.MainActivity.context;

/**
 * Created by nayir on 5/7/2017.
 */

public class InteractorImpl implements MoviesInteractor {
    MoviesPresenter Presenter;
    Uri uri;
    private MovieHelper mHelper;

    public InteractorImpl(MoviesPresenter present) {
        this.Presenter = present;

        mHelper = MovieHelper.getInstance();
    }


    @Override
    public void getMoviesOnLine() {
        callRetrofitMovies retrofitMovies = callRetrofitMovies.getInstance();
        retrofitMovies.retrofitCall(new NetworkResponse<ArrayList<movie>>() {
            @Override
            public void onSucess(ArrayList<movie> result) {
                for (movie film : result) {
                    if (mHelper.Moviefound(film.getId()) == null) {
                        mHelper.insertMovie(film);
                    }
                }
                Presenter.MoviesFeteched(result);
            }

            @Override
            public void onFailure(String message) {
                if (message.contains(context.getString(R.string.match_string))) {
                    boolean getDateFromDataBase = getMoviesFromDatabase(new ArrayList<movie>());
                    if (getDateFromDataBase)
                        Presenter.SendToast(context.getString(R.string.Chk_Con));
                    else
                        Presenter.MoviesUnFetched();
                } else
                    Presenter.SendToast(context.getString(R.string.Server_Error));
            }
        });
    }


    @Override
    public boolean getMoviesFromDatabase(ArrayList<movie> result) {
        int count = (int) movieProvider.getProfilesCount();
        int i;
        for (i = 1; i <= count; i++) {
            uri = ContentUris.withAppendedId(movieContract.movieEntry.CONTENT_URI, i);
            Cursor c = MyApplication.getContext().getContentResolver().query(uri, null, null, null, null);
            movie film = movieProvider.getfilm(c);
            result.add(film);
        }
        Presenter.MoviesFeteched(result);
        return result.size() != 0;
    }

    @Override
    public void getfilm(int filmid) {
        uri = ContentUris.withAppendedId(movieContract.movieEntry.CONTENT_URI, filmid);
        Cursor c = context.getContentResolver().query(uri, null, null, null, null);
        movie film = movieProvider.getfilm(c);
        if (film == null)
            Presenter.unFetchMovie();
        else
            Presenter.fetchMovie(film);

    }
}