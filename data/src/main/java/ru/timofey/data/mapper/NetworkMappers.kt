package ru.timofey.data.mapper

import ru.timofey.core_network.model.*
import ru.timofey.domain.model.*
import kotlinx.datetime.*


fun NetworkConference.toDomain(): Conference? = try {
    Conference(
        id = id.toLong(),
        name = name,
        format = format,
        status = status,
        statusTitle = status_title,
        urlSlug = url,
        image = image?.toDomain(),
        rating = rating?.toDouble(),
        startDate = start_date?.let { safeParseDate(it) },
        endDate = end_date?.let { safeParseDate(it) },
        oneDay = oneday == 1,
        countryId = country_id,
        country = country,
        cityId = city_id,
        city = city,
        categories = categories?.map { it.toDomain() } ?: emptyList(),
        typeId = type_id,
        typeName = type?.name,
        about = about,
        registerUrl = register_url
    )
} catch (e: Exception) {
    null
}

fun NetworkImage.toDomain() = Image(
    id = id,
    url = url,
    previewUrl = preview,
    placeholderColor = placeholder_color,
    width = width,
    height = height
)

fun NetworkCategory.toDomain() = Category(
    id = id,
    name = name,
    url = url
)

fun NetworkPagination.toDomain() = Pagination(
    pageSize = page_size ?: 0,
    currentPage = current_page ?: 1
)

private fun safeParseDate(raw: String): LocalDate? {
    return try {
        LocalDate.parse(raw)
    } catch (_: Exception) {
        null
    }
}
