package com.example.nayir.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nayir on 5/3/2017.
 */

public class trailerResponse {
    @SerializedName ( "id" )
    int id;


    @SerializedName ( "results" )
    ArrayList<trailers> trailers;

    public trailerResponse(int id, ArrayList<trailers> trailers) {
        this.id = id;
        this.trailers = trailers;
    }


    public int getId() {
        return id;
    }

    public ArrayList<trailers> getTrailers() {
        return trailers;
    }
}
