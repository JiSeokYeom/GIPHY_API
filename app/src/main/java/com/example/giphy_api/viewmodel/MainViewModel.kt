package com.example.giphy_api.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.giphy_api.GiphyApiService
import com.example.giphy_api.api.GiphyApi
import com.example.giphy_api.model.TrendingDTO
import com.example.giphy_api.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val TAG = "MainViewModel"
    private val result  = MutableLiveData<TrendingDTO>()
    private val giphyApiService = GiphyApiService()
    private val service  = RetrofitClient.getClient()?.create(GiphyApi::class.java)
    private val API_KEY = "0nYnzIFi1SjxgPRcMp4RmIPdiKMSEG02"

   /* fun getTrending():MutableLiveData<TrendingDTO>{
        result.value = giphyApiService.getApi()
        return result
    }*/
   fun getTrending():MutableLiveData<TrendingDTO>{
    val result : MutableLiveData<TrendingDTO> = MutableLiveData()
    val call : Call<TrendingDTO> = service!!.getTrendingGif(API_KEY,20)

    call.enqueue(object : Callback<TrendingDTO> {
        override fun onResponse(call: Call<TrendingDTO>, response: Response<TrendingDTO>) {
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

        override fun onFailure(call: Call<TrendingDTO>, t: Throwable) {
            Log.d(TAG,"통신 실패")
        }
    })
    return result
}
}