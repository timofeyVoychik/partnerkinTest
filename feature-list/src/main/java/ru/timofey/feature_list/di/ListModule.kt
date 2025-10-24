package ru.timofey.feature_list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ru.timofey.domain.repository.ConferenceRepository
import ru.timofey.domain.usecase.GetConferencesUseCase

@Module
@InstallIn(ViewModelComponent::class)
object ListModule {

    @Provides
    @ViewModelScoped
    fun provideGetConferencesUseCase(repository: ConferenceRepository): GetConferencesUseCase {
        return GetConferencesUseCase(repository)
    }
}
