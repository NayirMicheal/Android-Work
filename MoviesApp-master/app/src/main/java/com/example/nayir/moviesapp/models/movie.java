package com.example.nayir.moviesapp.models;

/**
 * Created by nayir on 4/25/2017.
 */

public class movie {
    private String name;
    private String releasedDate;
    private String overview;
    private String posterPath;

    private int id;

    public movie(String name, String releasedDate, String overview, String posterPath, int ID) {
        this.name = name;
        this.releasedDate = releasedDate;
        this.overview = overview;
        this.posterPath = posterPath;
        id = ID;
    }

    public String getName() {
        return name;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public int getId() {
        return id;
    }


}
