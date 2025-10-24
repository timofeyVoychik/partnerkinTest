package ru.timofey.domain.model

import kotlinx.datetime.*


data class Conference(
    val id: Long,
    val name: String,
    val format: String?,
    val status: String?,
    val statusTitle: String?,
    val urlSlug: String?,
    val image: Image?,
    val rating: Double?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val oneDay: Boolean,
    val countryId: Int?,
    val country: String?,
    val cityId: Int?,
    val city: String?,
    val categories: List<Category> = emptyList(),
    val typeId: Int?,
    val typeName: String?,
    val about: String?,
    val registerUrl: String?
)
