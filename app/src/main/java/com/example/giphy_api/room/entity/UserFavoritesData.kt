package com.example.giphy_api.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFavoritesData(
    @PrimaryKey
    var num : Int = 0,
    var url : String
)
