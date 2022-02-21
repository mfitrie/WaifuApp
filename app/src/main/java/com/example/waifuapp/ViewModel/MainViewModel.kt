package com.example.waifuapp.ViewModel

import android.app.Application
import android.media.MediaScannerConnection
import android.os.Environment
import android.util.Log
import androidx.lifecycle.*
import com.example.waifuapp.Repository.Repository
import com.example.waifuapp.model.AnimeQuote.AnimeQuoteData
import com.example.waifuapp.model.Waifu.WaifuJson
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception
import java.net.SocketTimeoutException

class MainViewModel(private val repository: Repository, context: Application): AndroidViewModel(context){

    val waifuResponse: MutableLiveData<Response<WaifuJson>> = MutableLiveData()
    val waifuResponseGif: MutableLiveData<Response<WaifuJson>> = MutableLiveData()
    val waifuResponseDownlaod: MutableLiveData<Response<ResponseBody>> = MutableLiveData()

    // Anime quote
    val animeQuoteResponse: MutableLiveData<Response<AnimeQuoteData>> = MutableLiveData()


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


    fun downloadWaifu(url: String){
        viewModelScope.launch {
            val response = repository.downloadWaifu(url)
            waifuResponseDownlaod.value = response

            val downloaded = saveImageToFile(response.body(), url)
            Log.d("DOWNLOAD", "downloadWaifu: $downloaded")

        }
    }

    private fun saveImageToFile(body: ResponseBody?, url: String): String{
        if (body!=null){
            Log.d("RESPONSE_BODY", "Response body = ${body.contentLength()}")
            var input: InputStream? = null
            val fileName = url.substring(url.lastIndexOf("/")+1)
//            val path = getApplication<Application>().filesDir.absolutePath + fileName
            val WaifuDir = File(Environment.getExternalStorageDirectory().absolutePath + "/" + Environment.DIRECTORY_PICTURES+ "/" + "WaifuApp/")
            WaifuDir.mkdirs()
            val path = WaifuDir.absolutePath + "/" + fileName

//            val path = Environment.getExternalStorageDirectory().absolutePath + "/" + Environment.DIRECTORY_PICTURES+ "/" + "WaifuApp/" + fileName

            MediaScannerConnection.scanFile(getApplication<Application>().applicationContext,
                arrayOf(path), null, MediaScannerConnection.OnScanCompletedListener(){ path, uri ->
                    Log.d("SCAN_MEDIA", "Successful scan to Picture")
                }
            )



            Log.d("SAVE_IMAGE_TO_FILE", "url = ${url}")
            Log.d("DIRECTORY", "saveImageToFile: $path")

            try {
                input = body.byteStream()
                val fileOutputStream = FileOutputStream(path)
                fileOutputStream.use { output ->
                    val buffer = ByteArray(4 * 1024)
                    var read: Int
                    while(input.read(buffer).also { read = it} != 1){
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                }
                // scan the gallery

                return path
            }catch (e: Exception){
                Log.d("SAVE_FILE", "saveImageToFile: ${e.toString()}")
            }finally {
                input?.close()
            }
        }


        return ""
    }



    // Get Anime quote
    fun getAnimeQuote(){
        viewModelScope.launch {
            val response = repository.getAnimeQuote()
            animeQuoteResponse.value = response
        }
    }



}