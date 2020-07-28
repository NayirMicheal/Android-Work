package com.example.nayir.moviesapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayir on 5/2/2017.
 */

public class review {
    @SerializedName ( "id" )
    String id;
    @SerializedName ( "author" )
    String author;
    @SerializedName ( "content" )
    String content;

    public review(String id, String author, String content) {
        this.id = id;
        this.author = author;
        this.content = content;
    }

    public String getIDMODEL() {
        return id;
    }

    public String getAUTHORMODEL() {
        return author;
    }

    public String getCONTENTMODEL() {
        return content;
    }

}
