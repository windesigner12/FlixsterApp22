package com.winshelosl.flixsterapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {


    int movieId;
    String title;
    String overview;
    String poster_path;
    String backdropImage;
    double vote_average;

    public Movie(){}


    public Movie(JSONObject jsonObject) throws JSONException {
        poster_path = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        backdropImage = jsonObject.getString("backdrop_path");
        vote_average = Double.parseDouble(jsonObject.getString("vote_average"));
        movieId = Integer.parseInt(jsonObject.getString("id"));

    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        ArrayList<Movie> movies = new ArrayList<>();

        for(int i = 0; i < movieJsonArray.length(); i++){
           movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
}

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", poster_path);

    }

    public String getBackdropImage() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropImage);
    }

    public boolean getVote_average() {
        if(vote_average > 6.0){
            return true;
        } else {
            return false;
        }
    }

    public double getVote(){
        return vote_average;
    }

    public int getMovieId() {
        return movieId;
    }
}
