package ru.timofey.core_db.entity

import androidx.room.*
import kotlinx.datetime.LocalDate

@Entity(tableName = "conferences")
data class ConferenceEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val format: String?,
    val status: String?,
    val statusTitle: String?,
    val urlSlug: String?,
    val rating: Double?,
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val oneDay: Boolean,
    val countryId: Int?,
    val country: String?,
    val cityId: Int?,
    val city: String?,
    val typeId: Int?,
    val typeName: String?,
    val about: String?,
    val registerUrl: String?
)

@Entity(
    tableName = "categories",
    primaryKeys = ["id", "conferenceId"]
)
data class CategoryEntity(
    val id: Int,
    val name: String,
    val url: String?,
    val conferenceId: Long
)

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey val id: String,
    val url: String?,
    val previewUrl: String?,
    val placeholderColor: String?,
    val width: Int?,
    val height: Int?,
    val conferenceId: Long
)
