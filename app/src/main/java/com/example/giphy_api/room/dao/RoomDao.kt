package com.example.giphy_api.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.giphy_api.room.entity.UserFavoritesData

@Dao
interface RoomDao {
    @Insert
    fun insert(userFavoritesData: UserFavoritesData)

    @Query("SELECT * FROM UserFavoritesData")
    fun getFavoritesAll():LiveData<List<UserFavoritesData>>

    @Query("DELETE FROM UserFavoritesData WHERE url = :url")
    fun delete(url : String)


}