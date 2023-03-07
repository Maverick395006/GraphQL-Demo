package com.maverick.graphqldemo.domain

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {

    suspend fun getCountryList(): List<SimpleCountry> {
        return countryClient
            .getCountries()
            .sortedBy { it.name }
    }

}