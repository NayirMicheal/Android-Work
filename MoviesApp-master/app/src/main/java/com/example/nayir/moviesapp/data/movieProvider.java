package com.example.nayir.moviesapp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.nayir.moviesapp.models.movie;

import static com.example.nayir.moviesapp.data.movieContract.movieEntry.TABLE_NAME;

/**
 * Created by nayir on 4/25/2017.
 */

public class movieProvider extends ContentProvider {
    final static int MOVIES = 100;
    final static int MOVIE_ID = 101;
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static movieDbHelper mDbHelper;

    static {

        // The calls to addURI() go here, for all of the content URI patterns that the provider
        // should recognize. All paths added to the UriMatcher have a corresponding code to return
        // when a match is found.
        sUriMatcher.addURI(movieContract.CONTENT_AUTHORITY, movieContract.PATH_MOVIES, MOVIES);
        sUriMatcher.addURI(movieContract.CONTENT_AUTHORITY, movieContract.PATH_MOVIES + "/#", MOVIE_ID);
    }

    public static movie getfilm(Cursor selected) {
        selected.moveToFirst();
        movie film = null;
        for (int i = 0; i < selected.getCount(); i++) {
            String name = selected.getString(selected.getColumnIndex(movieContract.movieEntry.COLUMN_MOVIE_NAME));
            String releasedDate = selected.getString(selected.getColumnIndex(movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE));
            String overView = selected.getString(selected.getColumnIndex(movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW));
            String posterPath = selected.getString(selected.getColumnIndex(movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH));
            int id = selected.getInt(selected.getColumnIndex(movieContract.movieEntry.COLUMN_MOVIE_ID));
            film = new movie(name, releasedDate, overView, posterPath, id);
            selected.moveToNext();
        }
        return film;
    }

    public static long getProfilesCount() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return cnt;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new movieDbHelper(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                cursor = database.query(TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case MOVIE_ID:
                selection = movieContract.movieEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return movieContract.movieEntry.CONTENT_LIST_TYPE;
            case MOVIE_ID:
                return movieContract.movieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }

    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return insertMovie(uri, values);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertMovie(Uri uri, ContentValues values) {
        String name = values.getAsString(movieContract.movieEntry.COLUMN_MOVIE_NAME);
        String releasedDate = values.getAsString(movieContract.movieEntry.COLUMN_MOVIE_RELEASEDDATE);
        String overview = values.getAsString(movieContract.movieEntry.COLUMN_MOVIE_OVERVIEW);
        String posterPath = values.getAsString(movieContract.movieEntry.COLUMN_MOVIE_POSTERPATH);
        int ID = values.getAsInteger(movieContract.movieEntry.COLUMN_MOVIE_ID);
        if (name == null) {
            throw new IllegalArgumentException("Movie requires a name");
        }
        if (releasedDate == null) {
            throw new IllegalArgumentException("Movie requires Release date");
        }
        if (overview == null) {
            throw new IllegalArgumentException("Movie requires overview");
        }
        if (posterPath == null) {
            throw new IllegalArgumentException("Movie requires posterPath");
        }
        if (String.valueOf(ID) == null) {
            throw new IllegalArgumentException("Movie requires an unique id");
        }
        SQLiteDatabase databasewrite = mDbHelper.getWritableDatabase();
        Long id = databasewrite.insert(TABLE_NAME, null, values);
        if (id == -1)
            return null;
//            Toast.makeText(getContext(), R.string.success_save, Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(getContext(), R.string.fail_save,Toast.LENGTH_LONG).show();
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int rowsDeleted;
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                // Delete all rows that match the selection and selection args
                rowsDeleted = database.delete(TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsDeleted;
            case MOVIE_ID:
                // Delete a single row given by the ID in the URI;
                selection = movieContract.movieEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(TABLE_NAME, selection, selectionArgs);
                if (rowsDeleted != 0)
                    getContext().getContentResolver().notifyChange(uri, null);
                return rowsDeleted;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case MOVIES:
                return updateMovie(uri, values, selection, selectionArgs);
            case MOVIE_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = movieContract.movieEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updateMovie(uri, values, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }

    }

    private int updateMovie(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        if (values.size() == 0) {
            return 0;
        }
        int rowsUpdated = database.update(TABLE_NAME, values, selection, selectionArgs);

        // Returns the number of database rows affected by the update statement
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
