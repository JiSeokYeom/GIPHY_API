package com.example.giphy_api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy_api.databinding.RvItemBinding
import com.example.giphy_api.model.list
import com.example.giphy_api.room.entity.UserFavoritesData

class FavoritesRvAdapter : RecyclerView.Adapter<FavoritesRvAdapter.ViewHolder>(){
    var trendingData = mutableListOf<UserFavoritesData>()

    inner class ViewHolder(var binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:UserFavoritesData){
            Glide.with(itemView.context)
                .load(item.url)
                .into(binding.itemImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trendingData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return trendingData.size
    }
}
