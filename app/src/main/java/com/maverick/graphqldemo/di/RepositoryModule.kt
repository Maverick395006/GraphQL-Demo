package com.maverick.graphqldemo.di

import com.maverick.graphqldemo.domain.GetCountriesUseCase
import com.maverick.graphqldemo.domain.GetCountryUseCase
import com.maverick.graphqldemo.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        getCountriesUseCase: GetCountriesUseCase,
        getCountryUseCase: GetCountryUseCase
    ): MainRepository = MainRepository(getCountriesUseCase, getCountryUseCase)

}