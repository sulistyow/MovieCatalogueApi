package com.sulistyo.moviecatalogueapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.data.model.TvFavorite
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.ui.activity.DetailTvActivity
import kotlinx.android.synthetic.main.item.view.*

class TvFavAdapter(private var mData: List<TvFavorite>) :
    RecyclerView.Adapter<TvFavAdapter.vHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        vHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: vHolder, position: Int) = holder.bind(mData.get(position))


    class vHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: TvFavorite) {
            Glide.with(itemView.context).load(ApiCall.img + data.posterPath).into(itemView.ivPoster)
            itemView.tvJudul.text = data.name
            itemView.setOnClickListener {
                val i = Intent(itemView.context, DetailTvActivity::class.java)
                i.putExtra(DetailTvActivity.DATA, data)
                i.putExtra(DetailTvActivity.STATE, "fav")
                itemView.context.startActivity(i)
            }
        }
    }
}