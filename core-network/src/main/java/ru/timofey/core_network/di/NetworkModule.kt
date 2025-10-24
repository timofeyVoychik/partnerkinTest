package ru.timofey.core_network.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.timofey.core_network.api.PartnerkinApi
import ru.timofey.core_network.checker.NetworkChecker
import ru.timofey.core_network.checker.NetworkCheckerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindNetworkChecker(
        impl: NetworkCheckerImpl
    ): NetworkChecker

    companion object {
        @Provides
        @Singleton
        fun providePartnerkinApi(): PartnerkinApi = PartnerkinApi()
    }
}
