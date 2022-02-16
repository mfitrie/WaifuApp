package com.example.waifuapp.Repository

import com.example.waifuapp.api.RetrofitInstance
import com.example.waifuapp.model.WaifuJson
import retrofit2.Response

class Repository {

    suspend fun getWaifuPic(): Response<WaifuJson>{
        return RetrofitInstance.api.getWaifuPic()
    }

}