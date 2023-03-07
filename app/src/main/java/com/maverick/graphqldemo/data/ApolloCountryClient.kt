package com.maverick.graphqldemo.data

import com.apollographql.apollo3.ApolloClient
import com.maverick.graphqldemo.CountriesQuery
import com.maverick.graphqldemo.CountryQuery
import com.maverick.graphqldemo.domain.*

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map {
                it.toSimpleCountry()
            }
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}