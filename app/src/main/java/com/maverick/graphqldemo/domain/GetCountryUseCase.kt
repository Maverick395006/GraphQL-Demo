package com.maverick.graphqldemo.domain

class GetCountryUseCase(
    private val countryClient: CountryClient
) {

    suspend fun getCountryDetails(code: String): DetailedCountry? {
        return countryClient.getCountry(code)
    }

}