package com.maverick.graphqldemo.domain

import com.maverick.graphqldemo.CountriesQuery
import com.maverick.graphqldemo.CountryQuery

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No Currency",
        languages = languages.mapNotNull {
            it.name
        },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital"
    )
}