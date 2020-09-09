package com.ironclad.mlplusandroid.networking

import com.ironclad.mlplusandroid.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("heartpredict/viz")
    suspend fun getResponse(@Query("vizdata[]") vizdata: List<String>): Response<Data>
}