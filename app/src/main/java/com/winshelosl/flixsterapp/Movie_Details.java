package com.winshelosl.flixsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.winshelosl.flixsterapp.models.Movie;

import org.parceler.Parcels;

public class Movie_Details extends YouTubeBaseActivity {

     TextView tvTitleD;
     TextView tvOverviewD;
     RatingBar ratingBar;
     YouTubePlayerView youTubePlayerView;

     public static String YOUTUBE_API_URL = "AIzaSyDWOho85kIR3Htcx9Su1TL6cEKWHs3CT7M";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        tvTitleD = findViewById(R.id.tvTitleD);
        tvOverviewD = findViewById(R.id.tvOverviewD);
        ratingBar = findViewById(R.id.ratingBar);
        youTubePlayerView = findViewById(R.id.player);


        youTubePlayerView.initialize(YOUTUBE_API_URL, new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        Log.d("Details Movie", "Onsucces");
                        youTubePlayer.cueVideo("5xVh-7ywKpE");
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                        Log.d("Details Movie", "OnFailure");
                    }
                });


                Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
       tvTitleD.setText(movie.getTitle());
       tvOverviewD.setText(movie.getOverview());
       ratingBar.setRating((float) movie.getVote());





    }
}