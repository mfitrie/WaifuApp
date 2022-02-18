package com.example.waifuapp

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waifuapp.Repository.Repository
import com.example.waifuapp.api.RetrofitInstance
import com.example.waifuapp.model.WaifuJson
import kotlinx.coroutines.*
import retrofit2.Response
import java.net.SocketTimeoutException
import kotlin.coroutines.coroutineContext

class MainViewModel(private val repository: Repository): ViewModel(){

    val waifuResponse: MutableLiveData<Response<WaifuJson>> = MutableLiveData()
    val waifuResponseGif: MutableLiveData<Response<WaifuJson>> = MutableLiveData()


    fun getWaifu(){

//        // handle connection timeout
//        val couroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
//            Log.d("SOCKET_TIMEOUT", "socket timeout")
//        }

        viewModelScope.launch {
            try {
                val response = repository.getWaifuPic()
                waifuResponse.value = response
            }catch (e: SocketTimeoutException){
                Log.d("SOCKET_TIMEOUT", "Connection timeout")
            }
        }
    }



    fun getWaifuGif(gif: Boolean){

//        // handle connection timeout
//        val couroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
//            Log.d("SOCKET_TIMEOUT", "socket timeout")
//        }

        viewModelScope.launch {
            try {
                val response = repository.getWaifuGif(gif)
                waifuResponseGif.value = response
            }catch (e: SocketTimeoutException){
                Log.d("SOCKET_TIMEOUT", "Connection timeout")
            }
        }
    }




}