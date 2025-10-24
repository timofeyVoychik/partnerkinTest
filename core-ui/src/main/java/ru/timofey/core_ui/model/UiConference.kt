package ru.timofey.core_ui.model

import kotlinx.datetime.LocalDate

data class UiConference(
    val id: Long,
    val name: String,
    val city: String?,
    val country: String?,
    val imageUrl: String?,
    val rating: Double?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val status: String?,
    val tags: List<String> = emptyList(),
    val isCancelled: Boolean = false,
    val about: String?,
    val registerUrl: String?,
    val categories: List<UiCategory> = emptyList(),
) {
    private val monthNamesRu = listOf(
        "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
        "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"
    )

    val startDay: String get() = startDate?.dayOfMonth?.toString() ?: ""
    val endDay: String get() = endDate?.dayOfMonth?.toString() ?: ""

    val month: String
        get() = startDate?.month?.let { monthNamesRu[it.ordinal] } ?: ""

    val location: String
        get() = when {
            !city.isNullOrEmpty() && !country.isNullOrEmpty() -> "$city, $country"
            !country.isNullOrEmpty() -> country
            else -> "Онлайн"
        }
}

data class UiCategory(
    val id: Int,
    val name: String,
    val url: String?
)