package com.osmanuslu.myapplication.services

import com.osmanuslu.myapplication.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    //http://217.79.178.34:8080/coinapi/

    @GET("getcoins?limit=50")
    fun getData(): Call<List<CryptoModel>>
}