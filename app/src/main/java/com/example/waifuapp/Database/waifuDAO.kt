package com.example.waifuapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.waifuapp.model.WaifuDB.WaifuDB

@Dao
interface waifuDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWaifu(waifu: WaifuDB)

    @Delete
    suspend fun deleteWaifu(waifu: WaifuDB)

    @Query("SELECT * FROM waifu_table ORDER BY waifu_ID ASC")
    fun readAllData(): LiveData<List<WaifuDB>>
}