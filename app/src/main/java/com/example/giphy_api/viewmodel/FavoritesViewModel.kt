package com.example.giphy_api.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.giphy_api.room.dao.TrendingRoomDao
import com.example.giphy_api.room.database.FavoritesDB
import com.example.giphy_api.room.entity.UserFavoritesData

class FavoritesViewModel(application: Application) : AndroidViewModel(Application()) {
    var userUrls : LiveData<List<UserFavoritesData>>
    private var trendingRoomDao : TrendingRoomDao

    init {
        val db = FavoritesDB.getInstance(application)
        trendingRoomDao = db!!.trendingRoomDao()
        userUrls = db.trendingRoomDao().getFavoritesAll()
    }

    fun delete(userUrl : String){
        trendingRoomDao.delete(userUrl)
    }


}