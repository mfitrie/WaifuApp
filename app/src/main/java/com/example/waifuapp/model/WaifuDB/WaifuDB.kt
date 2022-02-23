package com.example.waifuapp.model.WaifuDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waifu_table")
data class WaifuDB(
    @PrimaryKey(autoGenerate = true)
    val waifu_ID: Int,
    val url: String,
    val quote: String
)