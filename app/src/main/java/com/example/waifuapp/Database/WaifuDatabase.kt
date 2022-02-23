package com.example.waifuapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.waifuapp.model.WaifuDB.WaifuDB

@Database(entities = [WaifuDB::class], version = 1, exportSchema = false)
abstract class WaifuDatabase: RoomDatabase() {

    abstract fun waifuDAO(): waifuDAO

    companion object {
        @Volatile
        private var INSTANCE: WaifuDatabase? = null

        fun getDatabase(context: Context): WaifuDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WaifuDatabase::class.java,
                    "waifu_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}