package ru.timofey.domain.model


data class Pagination(
    val pageSize: Int,
    val currentPage: Int,
    val totalPages: Int? = null,
    val totalItems: Int? = null
)