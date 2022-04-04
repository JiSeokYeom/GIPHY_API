package com.example.giphy_api.retrofit

import com.example.giphy_api.MainActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofitClient : Retrofit? = null
    const val BASE_URL = "https://api.giphy.com/"

    fun getClient():Retrofit?{
        retrofitClient = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitClient
    }
}