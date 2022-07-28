package com.winshelosl.flixsterapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.winshelosl.flixsterapp.adapters.MovieAdapter;
import com.winshelosl.flixsterapp.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {


        private final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=6eb1e34600eb0fea035dd01d7668403f";
        private final String TAG = "MainActivity";
        List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies = findViewById(R.id.rvMovies);
        movies = new ArrayList<>();

        MovieAdapter movieAdapter = new MovieAdapter(this , movies);


        rvMovies.setAdapter(movieAdapter);

        rvMovies.setLayoutManager( new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {



            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;;
                Log.d(TAG, "OnSucess");

                try {
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.i(TAG,"Results:" + results.toString());
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e(TAG,"Hit json Exception", e);
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });



    }
}