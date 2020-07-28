package com.example.nayir.moviesapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayir on 5/3/2017.
 */

public class trailers {
    @SerializedName ( "id" )
    String id;
    @SerializedName ( "key" )
    String key;
    @SerializedName ( "name" )
    String name;
    @SerializedName ( "type" )
    String type;

    public trailers(String id, String key, String name, String type) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
