package com.example.waifuapp.Repository

import com.example.waifuapp.api.RetrofitInstance
import com.example.waifuapp.model.WaifuJson
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

}