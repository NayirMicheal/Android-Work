package com.example.nayir.newtweets;

/**
 * Created by nayir on 4/19/2017.
 */
public interface OnNetworkResponse {
    void onSucess(String result);

    void OnFailure(String fail);
}
