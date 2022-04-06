package com.example.giphy_api.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFavoritesData(
    var url : String
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
