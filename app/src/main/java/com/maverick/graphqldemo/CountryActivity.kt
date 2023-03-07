package com.maverick.graphqldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maverick.graphqldemo.databinding.ActivityCountryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}