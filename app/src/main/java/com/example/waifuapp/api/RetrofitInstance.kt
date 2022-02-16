package com.example.waifuapp.api

import com.example.waifuapp.util.Constant.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

//    val client = OkHttpClient.Builder()
//        .connectTimeout(10, TimeUnit.SECONDS)
//        .readTimeout(10, TimeUnit.SECONDS)
//        .build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: WaifuAPI by lazy {
        retrofit.create(WaifuAPI::class.java)
    }


}