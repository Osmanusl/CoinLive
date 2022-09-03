package com.osmanuslu.myapplication.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osmanuslu.myapplication.model.CryptoModel
import com.osmanuslu.myapplication.model.NewsModel
import com.osmanuslu.myapplication.services.CryptoAPI
import com.osmanuslu.myapplication.services.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsViewModel: ViewModel() {
    private val BASE_URL_NEWS="http://217.79.178.34:8080/"
    val data1 = MutableLiveData<List<NewsModel>>()

    init {
        loadData1()
    }
    private fun loadData1() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_NEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NewsAPI::class.java)

        val call = retrofit.getData()

        call.enqueue(object: Callback<List<NewsModel>> {
            override fun onResponse(call: Call<List<NewsModel>>, response: Response<List<NewsModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data1.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<NewsModel>>, t: Throwable) {
                t.printStackTrace()}
        })
    }

}