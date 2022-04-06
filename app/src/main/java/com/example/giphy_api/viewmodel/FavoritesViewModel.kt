package com.example.giphy_api.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.giphy_api.room.dao.RoomDao
import com.example.giphy_api.room.database.FavoritesDB
import com.example.giphy_api.room.entity.UserFavoritesData

class FavoritesViewModel(application: Application) : AndroidViewModel(Application()) {
    var userUrls : LiveData<List<UserFavoritesData>>
    private var roomDao : RoomDao

    init {
        val db = FavoritesDB.getInstance(application)
        roomDao = db!!.trendingRoomDao()
        userUrls = db.trendingRoomDao().getFavoritesAll()
    }

    fun delete(userUrl : String){
        roomDao.delete(userUrl)
    }


}