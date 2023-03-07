package com.maverick.graphqldemo.presentation

import androidx.lifecycle.*
import com.maverick.graphqldemo.domain.DetailedCountry
import com.maverick.graphqldemo.domain.GetCountriesUseCase
import com.maverick.graphqldemo.domain.GetCountryUseCase
import com.maverick.graphqldemo.domain.SimpleCountry
import com.maverick.graphqldemo.repository.MainRepository
import com.maverick.graphqldemo.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<SimpleCountry>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<SimpleCountry>>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                MainStateEvent.GetCountryList -> {
                    mainRepository.getCountryList()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }
                MainStateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}

sealed class MainStateEvent {
    object GetCountryList : MainStateEvent()
    object None : MainStateEvent()
}

//    fun selectCountry(code: String) {
//        viewModelScope.launch {
//            _state.update {
//                it.copy(
//                    selectedCountry = getCountryUseCase.execute(code)
//                )
//            }
//        }
//    }
//
//    fun dismissDialogue() {
//        _state.update {
//            it.copy(
//                selectedCountry = null
//            )
//        }
//    }

//    data class CountriesState(
//        val countries: List<SimpleCountry> = emptyList(),
//        val isLoading: Boolean = false,
//        val selectedCountry: DetailedCountry? = null
//    )
