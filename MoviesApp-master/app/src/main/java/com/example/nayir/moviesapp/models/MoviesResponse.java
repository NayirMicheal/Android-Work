package com.example.nayir.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nayir on 4/24/2017.
 */

public class MoviesResponse {
    @SerializedName ( "created_by" )
    String createdBy;
    @SerializedName ( "description" )
    String description;
    @SerializedName ( "favorite_count" )
    int favouriteCount;
    @SerializedName ( "id" )
    int id;
    @SerializedName ( "items" )
    ArrayList<filmsResponse> filmsResponses;
    @SerializedName ( "item_count" )
    int itemCount;
    @SerializedName ( "name" )
    String name;
    @SerializedName ( "poster_path" )
    String posterPath;

    public MoviesResponse(String createdBy, String description, int favouriteCount, int id
            , ArrayList<filmsResponse> filmsResponses, int itemCount, String name, String posterPath) {
        this.createdBy = createdBy;
        this.description = description;
        this.favouriteCount = favouriteCount;
        this.id = id;
        this.filmsResponses = filmsResponses;
        this.itemCount = itemCount;
        this.name = name;
        this.posterPath = posterPath;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getDescription() {
        return description;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public int getId() {
        return id;
    }

    public ArrayList<filmsResponse> getFilmsResponses() {
        return filmsResponses;
    }

    public int getItemCount() {
        return itemCount;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
