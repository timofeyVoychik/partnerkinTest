package ru.timofey.core_db.mappers

import ru.timofey.core_db.dao.ConferenceWithRelations
import ru.timofey.domain.model.*
import ru.timofey.core_db.entity.*

fun Conference.toEntity() = ConferenceEntity(
    id = id,
    name = name,
    format = format,
    status = status,
    statusTitle = statusTitle,
    urlSlug = urlSlug,
    rating = rating,
    startDate = startDate,
    endDate = endDate,
    oneDay = oneDay,
    countryId = countryId,
    country = country,
    cityId = cityId,
    city = city,
    typeId = typeId,
    typeName = typeName,
    about = about,
    registerUrl = registerUrl,
)

fun Category.toEntity(conferenceId: Long) = CategoryEntity(
    id = id,
    name = name,
    url = url,
    conferenceId = conferenceId
)

fun Image.toEntity(conferenceId: Long) = ImageEntity(
    id = id ?: "",
    url = url,
    previewUrl = previewUrl,
    placeholderColor = placeholderColor,
    width = width,
    height = height,
    conferenceId = conferenceId
)

fun ConferenceWithRelations.toDomain() = Conference(
    id = conference.id,
    name = conference.name,
    format = conference.format,
    status = conference.status,
    statusTitle = conference.statusTitle,
    urlSlug = conference.urlSlug,
    image = image?.let { Image(it.id, it.url, it.previewUrl, it.placeholderColor, it.width, it.height) },
    rating = conference.rating,
    startDate = conference.startDate,
    endDate = conference.endDate,
    oneDay = conference.oneDay,
    countryId = conference.countryId,
    country = conference.country,
    cityId = conference.cityId,
    city = conference.city,
    categories = categories.map { Category(it.id, it.name, it.url) },
    typeId = conference.typeId,
    typeName = conference.typeName,
    about = conference.about,
    registerUrl = conference.registerUrl,
)
