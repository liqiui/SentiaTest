package com.example.sentiatest.network

import com.example.sentiatest.data.Result
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private val BASE_URL = "https://f213b61d-6411-4018-a178-53863ed9f8ec.mock.pstmn.io/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    // callback version of getting data
    @GET("properties")
    fun getData(): Call<Result>

    // Coroutine version of getting data
    @GET("properties")
    suspend fun getDataCoroutine(): Result
}

object Api {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
