package com.osmanuslu.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.osmanuslu.myapplication.model.CryptoModel
import com.osmanuslu.myapplication.services.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {
//yaptığım apıye retrofit kullarak request atarak coinleri çekiyorum
    private val BASE_URL="http://217.79.178.34:8080/coinapi/"
    val data = MutableLiveData<List<CryptoModel>>()

    init {
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CryptoAPI::class.java)

        val call = retrofit.getData()

        call.enqueue(object: Callback<List<CryptoModel>> {
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        data.postValue(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()}
        })
    }
}