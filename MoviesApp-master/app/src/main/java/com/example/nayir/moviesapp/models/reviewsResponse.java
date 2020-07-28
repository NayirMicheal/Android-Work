package com.example.nayir.moviesapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nayir on 5/2/2017.
 */

public class reviewsResponse {


    @SerializedName ( "results" )
    ArrayList<review> result;

    public reviewsResponse(ArrayList<review> result) {
        this.result = result;
    }

    public reviewsResponse getinstance() {
        return this;
    }

    public ArrayList<review> getResult() {
        return result;
    }
}
