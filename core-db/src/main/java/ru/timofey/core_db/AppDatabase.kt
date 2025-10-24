package ru.timofey.core_db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.timofey.core_db.dao.ConferenceDao
import ru.timofey.core_db.entity.*

@Database(
    entities = [ConferenceEntity::class, CategoryEntity::class, ImageEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun conferenceDao(): ConferenceDao
}
