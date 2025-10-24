package ru.timofey.feature_conference.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import ru.timofey.domain.repository.ConferenceRepository
import ru.timofey.domain.usecase.GetConferenceUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConferenceFeatureModule {

    @Provides
    @Singleton
    fun provideGetConferenceDetailUseCase(
        repository: ConferenceRepository
    ): GetConferenceUseCase = GetConferenceUseCase(repository)
}
