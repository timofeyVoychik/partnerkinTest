package ru.timofey.core_db.dao

import androidx.room.*
import ru.timofey.core_db.entity.*

@Dao
interface ConferenceDao {

    @Transaction
    @Query("SELECT * FROM conferences ORDER BY startDate")
    suspend fun getConferences(): List<ConferenceWithRelations>

    @Transaction
    @Query("SELECT * FROM conferences WHERE id = :id")
    suspend fun getConferenceById(id: Long): ConferenceWithRelations?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConferences(conferences: List<ConferenceEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<ImageEntity>)
}


data class ConferenceWithRelations(
    @Embedded val conference: ConferenceEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "conferenceId"
    )
    val categories: List<CategoryEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "conferenceId"
    )
    val image: ImageEntity?
)
