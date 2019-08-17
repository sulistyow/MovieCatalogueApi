package com.sulistyo.moviecatalogueapi.ui.fragment.java;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sulistyo.moviecatalogueapi.R;
import com.sulistyo.moviecatalogueapi.adapter.java.TvShowAdapter;
import com.sulistyo.moviecatalogueapi.model.tv.DataTv;
import com.sulistyo.moviecatalogueapi.model.tv.TV;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

    private ArrayList<TV> tv = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.rvTvShow);
        recyclerView.setHasFixedSize(true);

        tv.addAll(DataTv.getTv());

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        TvShowAdapter mAdapter = new TvShowAdapter(getContext());
        mAdapter.setTvshow(tv);
        recyclerView.setAdapter(mAdapter);
    }
}
