package com.example.giphy_api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy_api.R
import com.example.giphy_api.databinding.RvItemBinding
import com.example.giphy_api.model.list
import com.example.giphy_api.room.entity.UserFavoritesData

class FavoritesRvAdapter : RecyclerView.Adapter<FavoritesRvAdapter.ViewHolder>(){
    var favoritesData = listOf<UserFavoritesData>()
    var selectSW = false

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener

    inner class ViewHolder(var binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item:UserFavoritesData){
            Glide.with(itemView.context)
                .load(item.url)
                .into(binding.itemImg)
            binding.FavoritesImg.setImageResource(R.drawable.star_pressed)

            binding.FavoritesImg.setOnClickListener {
               itemClickListener.onClick(it, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = favoritesData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return favoritesData.size
    }
}
