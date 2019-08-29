package com.sulistyo.moviecatalogueapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.data.MovieFavorite
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.ui.activity.DetailMoviesActivity
import kotlinx.android.synthetic.main.item.view.*
import org.jetbrains.anko.toast

class MovieFavAdapter(private var mData: List<MovieFavorite>) :
    RecyclerView.Adapter<MovieFavAdapter.vHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        vHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: vHolder, position: Int) = holder.bind(mData.get(position))

    class vHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: MovieFavorite) {
            Glide.with(itemView.context).load(ApiCall.img + data.posterPath).into(itemView.ivPoster)
            itemView.tvJudul.text = data.title
            itemView.setOnClickListener {
                val i = Intent(itemView.context, DetailMoviesActivity::class.java)
                i.putExtra(DetailMoviesActivity.DATA, data)
                i.putExtra(DetailMoviesActivity.STATE, "fav")
                itemView.context.startActivity(i)
            }
        }
    }
}