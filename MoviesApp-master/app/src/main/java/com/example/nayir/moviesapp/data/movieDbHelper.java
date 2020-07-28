package com.example.nayir.moviesapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nayir on 4/25/2017.
 */

public class movieDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE if not exists " + movieContract.movieEntry.TABLE_NAME + " (" +
                    movieContract.movieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    movieContract.movieEntry.COLUMN_MOVIE_NAME + " TEXT NOT NULL, " +
                    movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE + " TEXT NOT NULL, " +
                    movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL, " +
                    movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH + " TEXT NOT NULL," +
                    movieContract.movieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL);";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + movieContract.movieEntry.TABLE_NAME + ";";

    public movieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
