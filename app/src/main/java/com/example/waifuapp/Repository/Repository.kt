package com.example.waifuapp.Repository

import androidx.lifecycle.LiveData
import com.example.waifuapp.Database.waifuDAO
import com.example.waifuapp.api.RetrofitInstance
import com.example.waifuapp.model.AnimeQuote.AnimeQuoteData
import com.example.waifuapp.model.Waifu.WaifuJson
import com.example.waifuapp.model.WaifuDB.WaifuDB
import okhttp3.ResponseBody
import retrofit2.Response

class Repository {

    suspend fun getWaifuPic(): Response<WaifuJson>{
        return RetrofitInstance.api.getWaifuPic()
    }

    suspend fun getWaifuGif(gif: Boolean): Response<WaifuJson>{
        return RetrofitInstance.api.getWaifuGif(gif)
    }

    // download image
    suspend fun downloadWaifu(url: String): Response<ResponseBody>{
        return RetrofitInstance.api.downloadWaifu(url)
    }


    // Anime quote
    suspend fun getAnimeQuote(): Response<AnimeQuoteData>{
        return RetrofitInstance.api.getAnimeQuote()
    }

}