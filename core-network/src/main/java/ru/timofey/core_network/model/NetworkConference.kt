package ru.timofey.core_network.model

data class NetworkConference(
    val id: Long,
    val name: String,
    val format: String?,
    val status: String?,
    val status_title: String?,
    val url: String?,
    val image: NetworkImage?,
    val rating: Float?,
    val start_date: String?,
    val end_date: String?,
    val oneday: Int?,
    val custom_date: String?,
    val country_id: Int?,
    val country: String?,
    val city_id: Int?,
    val city: String?,
    val categories: List<NetworkCategory>?,
    val type_id: Int?,
    val type: NetworkType?,
    val register_url: String?,
    val about: String?
)

data class NetworkImage(
    val id: String?,
    val url: String?,
    val preview: String?,
    val placeholder_color: String?,
    val width: Int?,
    val height: Int?
)

data class NetworkCategory(
    val id: Int,
    val name: String,
    val url: String
)

data class NetworkType(
    val id: Int,
    val name: String
)
