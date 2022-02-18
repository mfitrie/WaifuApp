package com.example.waifuapp.api

import com.example.waifuapp.model.WaifuJson
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WaifuAPI {

    @Headers("Content-Type: application/json")
    @GET("sfw/waifu")
    suspend fun getWaifuPic(): Response<WaifuJson>

    @GET("sfw/waifu")
    suspend fun getWaifuGif(
        @Query("gif") gif: Boolean
    ): Response<WaifuJson>

}