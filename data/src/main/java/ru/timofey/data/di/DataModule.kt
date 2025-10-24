package ru.timofey.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.timofey.core_db.dao.ConferenceDao
import ru.timofey.core_network.api.PartnerkinApi
import ru.timofey.core_network.checker.NetworkChecker
import ru.timofey.data.repository.ConferenceRepositoryImpl
import ru.timofey.data.repository.LocalConferenceRepository
import ru.timofey.domain.repository.ConferenceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideLocalConferenceRepository(dao: ConferenceDao): LocalConferenceRepository {
        return LocalConferenceRepository(dao)
    }

    @Provides
    @Singleton
    fun provideConferenceRepository(
        api: PartnerkinApi,
        localRepo: LocalConferenceRepository,
        checker: NetworkChecker
    ): ConferenceRepository = ConferenceRepositoryImpl(api, localRepo, checker)


}
