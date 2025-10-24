package ru.timofey.domain.model

data class Image(
    val id: String?,
    val url: String?,
    val previewUrl: String?,
    val placeholderColor: String?,
    val width: Int?,
    val height: Int?
)
