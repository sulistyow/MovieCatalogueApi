package com.sulistyo.moviecatalogueapi.ui.activity.java;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.sulistyo.moviecatalogueapi.R;
import com.sulistyo.moviecatalogueapi.model.movie.Movie;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "movie";
    TextView judul, deskripsi;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        judul = findViewById(R.id.tvJudul);
        deskripsi = findViewById(R.id.tvDeskripsi);
        poster = findViewById(R.id.ivDetail);

        Movie movi = getIntent().getParcelableExtra(EXTRA_MOVIE);
        judul.setText(movi.getTitle());
        deskripsi.setText(movi.getDesc());
        Glide.with(this).load(movi.getPhoto())
                .into(poster);
    }
}
