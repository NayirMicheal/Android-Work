package com.example.nayir.newtweets;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayir on 4/19/2017.
 */
public class TwitterResponse {
    @SerializedName("created_at")
    private String Time;

    @SerializedName("text")
    private String Tweet;

    //    @SerializedName("profile_image_url")
//private String  image_url;
    TwitterResponse(String Tweet, String Time/*String Image*/) {
        this.Time = Time;
        this.Tweet = Tweet;
//        this.image_url = Image;
    }

    public String getTime() {
        return Time;
    }

    public String getTweet() {
        return Tweet;
    }
//
//    public String getImage_url() {
//        return image_url;
//    }
}
