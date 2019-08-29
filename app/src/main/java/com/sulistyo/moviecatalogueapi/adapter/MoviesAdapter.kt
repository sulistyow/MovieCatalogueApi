package com.sulistyo.moviecatalogueapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.movie.kt.DataMovies
import com.sulistyo.moviecatalogueapi.ui.activity.DetailMoviesActivity
import kotlinx.android.synthetic.main.item.view.*

class MoviesAdapter(private var mData: List<DataMovies>) :
    RecyclerView.Adapter<MoviesAdapter.vHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        vHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )

    fun updateData(mData: List<DataMovies>) {
        this.mData = mData
        notifyDataSetChanged()
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: vHolder, position: Int) = holder.bind(mData.get(position))

    class vHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataMovies) {
            Glide.with(itemView.context).load(ApiCall.img + data.posterPath).into(itemView.ivPoster)
            itemView.tvJudul.text = data.title
            itemView.setOnClickListener {
                val i = Intent(itemView.context, DetailMoviesActivity::class.java)
                i.putExtra(DetailMoviesActivity.DATA, data)
                i.putExtra(DetailMoviesActivity.STATE, "movie")
                itemView.context.startActivity(i)
            }
        }
    }
}