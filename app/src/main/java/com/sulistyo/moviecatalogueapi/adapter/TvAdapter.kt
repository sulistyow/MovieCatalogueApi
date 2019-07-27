package com.sulistyo.moviecatalogueapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sulistyo.moviecatalogueapi.R
import com.sulistyo.moviecatalogueapi.helper.networking.ApiCall
import com.sulistyo.moviecatalogueapi.model.tv.DataTv
import kotlinx.android.synthetic.main.item_data.view.*

class TvAdapter(private val mData: List<DataTv>, val listener: (DataTv) -> Unit) :
    RecyclerView.Adapter<TvAdapter.vHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = vHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
    )

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: vHolder, position: Int) = holder.bind(mData.get(position), listener)


    class vHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DataTv, listen: (DataTv) -> Unit) {
            Glide.with(itemView.context).load(ApiCall.img + data.posterPath).into(itemView.ivPoster)
            itemView.tvJudul.text = data.name
            itemView.setOnClickListener { listen(data) }
        }
    }
}