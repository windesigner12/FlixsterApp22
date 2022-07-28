package com.winshelosl.flixsterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.winshelosl.flixsterapp.models.Movie;

import org.parceler.Parcels;

public class Movie_Details extends AppCompatActivity {

     TextView tvTitleD;
     TextView tvOverviewD;
     RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

            tvTitleD = findViewById(R.id.tvTitleD);
        tvOverviewD = findViewById(R.id.tvOverviewD);
        ratingBar = findViewById(R.id.ratingBar);


  Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
       tvTitleD.setText(movie.getTitle());
       tvOverviewD.setText(movie.getOverview());
       ratingBar.setRating((float) movie.getVote());

    }
}