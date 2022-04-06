package com.example.giphy_api.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.giphy_api.room.dao.RoomDao
import com.example.giphy_api.room.entity.UserFavoritesData

@Database(entities = [UserFavoritesData::class], version = 2)
abstract class FavoritesDB : RoomDatabase(){
    abstract fun trendingRoomDao():RoomDao

    companion object{
        private var instance : FavoritesDB? = null

        @Synchronized
        fun getInstance(context: Context):FavoritesDB?{
            if (instance == null){
                synchronized(FavoritesDB::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoritesDB::class.java,
                        "favorites_databse"
                    ).build()
                }
            }
            return instance
        }
    }
}