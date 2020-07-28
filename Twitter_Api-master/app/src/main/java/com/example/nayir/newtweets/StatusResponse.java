package com.example.nayir.newtweets;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nayir on 4/19/2017.
 */
public class StatusResponse {
    @SerializedName("statuses")
    ArrayList<TwitterResponse> tweetresponse;

    StatusResponse(ArrayList<TwitterResponse> responses) {
        tweetresponse = responses;
    }
}
