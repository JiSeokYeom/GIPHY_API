package com.example.giphy_api.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.giphy_api.api.GiphyApi
import com.example.giphy_api.model.TrendingData
import com.example.giphy_api.retrofit.RetrofitClient
import com.example.giphy_api.room.dao.TrendingRoomDao
import com.example.giphy_api.room.database.FavoritesDB
import com.example.giphy_api.room.entity.UserFavoritesData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingViewModel(application: Application) : AndroidViewModel(Application()) {
    private val TAG = "MainViewModel"
    private val service  = RetrofitClient.getClient()?.create(GiphyApi::class.java)
    private val API_KEY = "0nYnzIFi1SjxgPRcMp4RmIPdiKMSEG02"
    private var trendingRoomDao : TrendingRoomDao


    init {
        val db = FavoritesDB.getInstance(application)
        trendingRoomDao = db!!.trendingRoomDao()
    }

    fun insert(userUrl : UserFavoritesData){
        trendingRoomDao.insert(userUrl)
    }

    fun delete(userUrl: String){
        trendingRoomDao.delete(userUrl)
    }

   fun getTrending(pageOffset : Int):MutableLiveData<TrendingData>{
    val result : MutableLiveData<TrendingData> = MutableLiveData()
    val call : Call<TrendingData> = service!!.getTrendingGif(API_KEY,21,pageOffset)

    call.enqueue(object : Callback<TrendingData> {
        override fun onResponse(call: Call<TrendingData>, response: Response<TrendingData>) {
            Log.d(TAG,"통신 성공")

            if (response.isSuccessful){
                // 응답을 잘 받은 경우
                Log.d(TAG,"통신과 응답 성공")
                Log.d(TAG,"${response.body()}")

                result.value = response.body()
            }
            else {
                Log.d(TAG,"통신에 성공 하지만 응답 실패")
            }
        }

        override fun onFailure(call: Call<TrendingData>, t: Throwable) {
            Log.d(TAG,"통신 실패")
        }
    })
    return result
}
}