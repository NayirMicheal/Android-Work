package com.example.nayir.moviesapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nayir on 4/24/2017.
 */

public class filmsResponse {
    @SerializedName ( "poster_path" )
    String posterPath;
    @SerializedName ( "adult" )
    Boolean adult;
    @SerializedName ( "overview" )
    String overView;
    @SerializedName ( "release_date" )
    String releaseDate;
    @SerializedName ( "original_title" )
    String originalTitle;
    @SerializedName ( "id" )
    int id;
    @SerializedName ( "media_type" )
    String mediaType;
    @SerializedName ( "original_language" )
    String originalLanguage;
    @SerializedName ( "title" )
    String title;
    @SerializedName ( "backdrop_path" )
    String backDropPath;
    @SerializedName ( "popularity" )
    String popularity;
    @SerializedName ( "vote_count" )
    int voteCount;
    @SerializedName ( "video" )
    Boolean video;
    @SerializedName ( "vote_average" )
    float voteAverage;

    public filmsResponse(String posterPath, Boolean adult, String overView, String releaseDate, String originalTitle
            , String originalLanguage, String title, String backDropPath, String popularity, int voteCount
            , float voteAverage, Boolean video, int id, String mediaType) {
        this.posterPath = posterPath;
        this.adult = adult;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.title = title;
        this.backDropPath = backDropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.video = video;
        this.id = id;
        this.mediaType = mediaType;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverView() {
        return overView;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public int getId() {
        return id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }
}
