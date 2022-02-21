package com.example.waifuapp.model.Waifu

data class Image(
    val dominant_color: String,
    val extension: String,
    val `file`: String,
    val image_id: Int,
    val like: Int,
    val preview_url: String,
    val source: String,
    val tags: List<Tag>,
    val uploaded_at: String,
    val url: String
)