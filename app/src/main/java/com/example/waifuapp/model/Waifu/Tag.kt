package com.example.waifuapp.model.Waifu

data class Tag(
    val description: String,
    val is_nsfw: Boolean,
    val name: String,
    val tag_id: Int
)