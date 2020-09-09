package com.ironclad.mlplusandroid.networking

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://heart-prediction.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiCall = retrofit().create(RetrofitInterface::class.java)
}