package com.example.giphy_api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.giphy_api.api.GiphyApi
import com.example.giphy_api.model.TrendingData
import com.example.giphy_api.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GiphyApiService {
    private val service  = RetrofitClient.getClient()?.create(GiphyApi::class.java)
    private val API_KEY = "0nYnzIFi1SjxgPRcMp4RmIPdiKMSEG02"
    private val TAG = "GiphyApiService"


    fun getApi() : MutableLiveData<TrendingData>{
        val result : MutableLiveData<TrendingData> = MutableLiveData()
        val call : Call<TrendingData> = service!!.getTrendingGif(API_KEY,20)

        call.enqueue(object : Callback<TrendingData>{
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