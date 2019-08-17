package com.sulistyo.moviecatalogueapi.ui.fragment.java;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sulistyo.moviecatalogueapi.R;
import com.sulistyo.moviecatalogueapi.adapter.java.MovieAdapter;
import com.sulistyo.moviecatalogueapi.model.movie.DataMovie;
import com.sulistyo.moviecatalogueapi.model.movie.Movie;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    private ArrayList<Movie> movies = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvMovie);
        recyclerView.setHasFixedSize(true);

        movies.addAll(DataMovie.getMovieList());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        MovieAdapter mAdapter = new MovieAdapter(getContext());
        mAdapter.setMovie(movies);
        recyclerView.setAdapter(mAdapter);
    }


}
