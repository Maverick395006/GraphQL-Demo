package com.maverick.graphqldemo.repository

import android.util.Log
import com.google.gson.Gson
import com.maverick.graphqldemo.domain.DetailedCountry
import com.maverick.graphqldemo.domain.GetCountriesUseCase
import com.maverick.graphqldemo.domain.GetCountryUseCase
import com.maverick.graphqldemo.domain.SimpleCountry
import com.maverick.graphqldemo.utils.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) {

    suspend fun getCountryList(): Flow<DataState<List<SimpleCountry>>> = flow {

        // Loading State
        emit(DataState.Loading)
        delay(1500)
        try {
            // get data by Api Call
            val networkCountryList = getCountriesUseCase.getCountryList()
            Log.e("API Response", Gson().toJson(networkCountryList))

            // Success State: Finally emit data  as Single Source of truth
            emit(DataState.Success(networkCountryList))
        } catch (e: Exception) {
            // Error State
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getCountry(code: String): Flow<DataState<DetailedCountry>> = flow {

        // Loading State
        emit(DataState.Loading)
        delay(1500)
        try {
            // get data by Api Call
            val networkCountry = getCountryUseCase.getCountryDetails(code)
            Log.e("API Response", Gson().toJson(networkCountry))

            // Success State: Finally emit data  as Single Source of truth
            emit(DataState.Success(networkCountry!!))
        } catch (e: Exception) {
            // Error State
            emit(DataState.Error(e))
        }
    }.flowOn(Dispatchers.IO)

}