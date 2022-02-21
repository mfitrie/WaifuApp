package com.example.waifuapp.api

import com.example.waifuapp.model.AnimeQuote.AnimeQuoteData
import com.example.waifuapp.model.Waifu.WaifuJson
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface WaifuAPI {

    @Headers("Content-Type: application/json")
    @GET("sfw/waifu")
    suspend fun getWaifuPic(): Response<WaifuJson>

    @GET("sfw/waifu")
    suspend fun getWaifuGif(
        @Query("gif") gif: Boolean
    ): Response<WaifuJson>

    // Download the picture
    @Streaming
    @GET
    suspend fun downloadWaifu(@Url url: String): Response<ResponseBody>


    // Anime quote
    @GET("https://animechan.vercel.app/api/random")
    suspend fun getAnimeQuote(): Response<AnimeQuoteData>

}