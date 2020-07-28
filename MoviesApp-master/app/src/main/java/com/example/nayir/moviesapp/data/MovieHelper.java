package com.example.nayir.moviesapp.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.nayir.moviesapp.MyApplication;
import com.example.nayir.moviesapp.models.movie;

/**
 * Created by nayir on 4/26/2017.
 */

public class MovieHelper {
    static Context context;
    private Uri uri;

    private MovieHelper(Context context) {
        MovieHelper.context = context;
    }

    public static MovieHelper getInstance() {
        context = MyApplication.getContext();
        return new MovieHelper(context);
    }

    public int insertMovie(movie film) {
        ContentValues values = new ContentValues();
        values.put(movieContract.movieEntry.COLUMN_MOVIE_NAME, film.getName());
        values.put(movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE, film.getReleasedDate());
        values.put(movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW, film.getOverview());
        values.put(movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH, film.getPosterPath());
        values.put(movieContract.movieEntry.COLUMN_MOVIE_ID, film.getId());
        Uri URI_ID = context.getContentResolver().insert(movieContract.movieEntry.CONTENT_URI, values);
        if (!(URI_ID == null)) {
            return 1;
        } else {
            return -1;
        }
    }

    public int updateMovie(int id, movie film) {
        uri = ContentUris.withAppendedId(movieContract.movieEntry.CONTENT_URI, id);
        ContentValues values = new ContentValues();
        if (film.getName() != "")
            values.put(movieContract.movieEntry.COLUMN_MOVIE_NAME, film.getName());
        if (film.getReleasedDate() != "")
            values.put(movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE, film.getReleasedDate());
        if (film.getOverview() != "")
            values.put(movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW, film.getOverview());
        if (film.getPosterPath() != "")
            values.put(movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH, film.getPosterPath());
        if (String.valueOf(film.getId()) != "")
            values.put(movieContract.movieEntry.COLUMN_MOVIE_ID, film.getId());
        int rowAffected = context.getContentResolver().update(uri, values, null, null);
        return rowAffected;

    }

    public int deleteMovie(int id) {
        uri = ContentUris.withAppendedId(movieContract.movieEntry.CONTENT_URI, id);
        int rowsDeleted = context.getContentResolver().delete(uri, null, null);
        return rowsDeleted;
    }

    public movie searchMovie(String searchname) {
        String[] projection = {movieContract.movieEntry._ID,
                movieContract.movieEntry.COLUMN_MOVIE_NAME,
                movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE,
                movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW,
                movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH,
                movieContract.movieEntry.COLUMN_MOVIE_ID};
        String selection = movieContract.movieEntry.COLUMN_MOVIE_NAME + "=?";
        String selectionArgs[] = new String[]{searchname};
        Cursor selected = context.getContentResolver().query(movieContract.movieEntry.CONTENT_URI, projection, selection, selectionArgs, null);
        movie film = movieProvider.getfilm(selected);
        return film;
    }

    public movie Moviefound(int id) {
        String[] projection = {movieContract.movieEntry._ID,
                movieContract.movieEntry.COLUMN_MOVIE_NAME,
                movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE,
                movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW,
                movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH,
                movieContract.movieEntry.COLUMN_MOVIE_ID};
        String selection = movieContract.movieEntry.COLUMN_MOVIE_ID + "=?";
        String selectionArgs[] = new String[]{String.valueOf(id)};
        Cursor selected = context.getContentResolver().query(movieContract.movieEntry.CONTENT_URI, projection, selection, selectionArgs, null);
        movie film = movieProvider.getfilm(selected);
        return film;
    }

}
