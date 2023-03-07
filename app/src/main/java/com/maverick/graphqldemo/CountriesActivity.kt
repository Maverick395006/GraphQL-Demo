package com.maverick.graphqldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.maverick.graphqldemo.databinding.ActivityCountriesBinding
import com.maverick.graphqldemo.domain.SimpleCountry
import com.maverick.graphqldemo.presentation.CountriesViewModel
import com.maverick.graphqldemo.presentation.MainStateEvent
import com.maverick.graphqldemo.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountriesBinding

    private val viewModel: CountriesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetCountryList)

    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<SimpleCountry>> -> {
                    binding.tvCountries.text = dataState.data.toString()
                }
                is DataState.Error -> {
                    binding.tvCountries.text = "Error..."
                }
                is DataState.Loading -> {
                    binding.tvCountries.text = "Loading..."
                }
            }
        }
    }
}