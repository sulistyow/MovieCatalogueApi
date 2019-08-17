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
import com.sulistyo.moviecatalogueapi.model.tv.TV;
import com.sulistyo.moviecatalogueapi.ui.activity.java.DetailTvShowActivity;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TV> tvshow;

    private ArrayList<TV> getTvshow() {
        return tvshow;
    }

    public void setTvshow(ArrayList<TV> tvshow) {
        this.tvshow = tvshow;
    }

    public TvShowAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvTitle.setText(getTvshow().get(position).getTitle());
        Glide.with(context)
                .load(getTvshow().get(position).getPhoto())
                .into(viewHolder.ivPoster);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TV tv = new TV();
                tv.setTitle(getTvshow().get(position).getTitle());
                tv.setDesc(getTvshow().get(position).getDesc());
                tv.setPhoto(getTvshow().get(position).getPhoto());

                Intent i = new Intent(context, DetailTvShowActivity.class);
                i.putExtra(DetailTvShowActivity.EXTRA_TV, tv);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getTvshow().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        LinearLayout linearLayout;
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTitle = itemView.findViewById(R.id.tvJudul);
            linearLayout = itemView.findViewById(R.id.layout);
        }
    }
}

