package ru.timofey.core_db.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.timofey.core_db.AppDatabase
import ru.timofey.core_db.dao.ConferenceDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDbModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "conference_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideConferenceDao(database: AppDatabase): ConferenceDao {
        return database.conferenceDao()
    }
}
