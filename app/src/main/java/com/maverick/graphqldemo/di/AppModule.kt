package com.maverick.graphqldemo.di

import com.apollographql.apollo3.ApolloClient
import com.maverick.graphqldemo.data.ApolloCountryClient
import com.maverick.graphqldemo.domain.CountryClient
import com.maverick.graphqldemo.domain.GetCountriesUseCase
import com.maverick.graphqldemo.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient =
        ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient =
        ApolloCountryClient(apolloClient)

    @Provides
    @Singleton
    fun provideCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase =
        GetCountriesUseCase(countryClient)

    @Provides
    @Singleton
    fun provideCountryUseCase(countryClient: CountryClient): GetCountryUseCase =
        GetCountryUseCase(countryClient)

}