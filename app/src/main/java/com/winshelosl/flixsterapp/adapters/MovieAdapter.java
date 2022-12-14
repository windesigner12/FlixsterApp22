package com.winshelosl.flixsterapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.winshelosl.flixsterapp.MainActivity;
import com.winshelosl.flixsterapp.Movie_Details;
import com.winshelosl.flixsterapp.R;
import com.winshelosl.flixsterapp.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemViewType(int position){
        if(movies.get(position).getVote_average() == true && context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            return R.layout.item_vote;
        } else {
            return R.layout.item_movie;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
//        return new ViewHolder(movieView);
        View view;
        final RecyclerView.ViewHolder holder;

        switch (viewType){

            case R.layout.item_vote:
                view = LayoutInflater.from(context).inflate(R.layout.item_vote, parent, false);
                holder = new ViewHolderVoteCount(view);
                break;
            default:
                view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
                holder = new ViewHolderPortrait(view);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof ViewHolderPortrait){
           Movie movie = movies.get(position);
           ((ViewHolderPortrait) holder).bind(movie);
        } else if(holder instanceof  ViewHolderVoteCount){
           Movie movie = movies.get(position);
           ((ViewHolderVoteCount) holder).bind(movie);
        }
    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderPortrait extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;


        public ViewHolderPortrait(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            String getUrlPath;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                getUrlPath = movie.getBackdropImage();
            } else {
                getUrlPath = movie.getPoster_path();
            }
            Glide.with(context)
                    .load(getUrlPath)
                    .into(ivPoster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Movie_Details.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    Pair<View, String> trans1 = Pair.create(tvTitle , "title");
                    Pair<View, String> trans2 = Pair.create(tvOverview , "overview");
                    Pair<View, String> trans3 = Pair.create(ivPoster , "poster");

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, trans1,trans2,trans3);
                    context.startActivity(i, options.toBundle());

                }
            });


        }
    }


    public class ViewHolderVoteCount extends RecyclerView.ViewHolder{

        ImageView ivPosterVote;
        RelativeLayout container;

        public ViewHolderVoteCount(@NonNull View itemView) {
            super(itemView);
            ivPosterVote = itemView.findViewById(R.id.ivPosterVote);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Movie movie) {
//            tvTitle.setText(movie.getTitle());
//            tvOverview.setText(movie.getOverview());

//            String getUrlPath;
//            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
//                getUrlPath = movie.getBackdropImage();
//            } else {
//                getUrlPath = movie.getPoster_path();
//            }
            Glide.with(context)
                    .load(movie.getBackdropImage())
                    .into(ivPosterVote);


            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Movie_Details.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    Log.d("ADAPTER", ivPosterVote.toString());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, (View) ivPosterVote, "profile");
                    context.startActivity(i, options.toBundle());
                }
            });
        }
    }
}
