package com.sulistyo.moviecatalogueapi.adapter.java;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.sulistyo.moviecatalogueapi.R;
import com.sulistyo.moviecatalogueapi.model.movie.Movie;
import com.sulistyo.moviecatalogueapi.ui.activity.java.DetailMovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    private ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovie(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvJudul.setText(getMovies().get(position).getTitle());
        Glide.with(context)
                .load(getMovies().get(position).getPhoto())
                .into(viewHolder.ivPoster);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Movie movi = new Movie();
                movi.setTitle(getMovies().get(position).getTitle());
                movi.setDesc(getMovies().get(position).getDesc());
                movi.setPhoto(getMovies().get(position).getPhoto());

                Intent i = new Intent(context, DetailMovieActivity.class);
                i.putExtra(DetailMovieActivity.EXTRA_MOVIE, movi);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getMovies().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        LinearLayout linearLayout;
        TextView tvJudul;

        ViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            linearLayout = itemView.findViewById(R.id.layout);
        }
    }
}

