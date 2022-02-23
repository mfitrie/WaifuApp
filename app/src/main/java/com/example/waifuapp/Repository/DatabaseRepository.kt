package com.example.waifuapp.Repository

import androidx.lifecycle.LiveData
import com.example.waifuapp.Database.waifuDAO
import com.example.waifuapp.model.WaifuDB.WaifuDB

class DatabaseRepository(private val waifuDAO: waifuDAO) {

    val readAllData: LiveData<List<WaifuDB>> = waifuDAO.readAllData()

    suspend fun addWaifu(waifuDB: WaifuDB){
        waifuDAO.addWaifu(waifuDB)
    }
}