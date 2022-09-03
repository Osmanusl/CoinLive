package com.osmanuslu.myapplication.services


import com.osmanuslu.myapplication.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    //http://217.79.178.34:8080/

    @GET("coinapi/newsapi")
    fun getData(): Call<List<NewsModel>>
}