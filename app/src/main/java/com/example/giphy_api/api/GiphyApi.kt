package com.example.giphy_api.api

import com.example.giphy_api.model.TrendingDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {
    @GET("/v1/gifs/trending")
    fun getTrendingGif(
        @Query("api_key") apiKey : String,
        @Query("limit") limit : Int
    ) : Call<TrendingDTO>
}