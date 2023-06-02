package com.android_wavelength.medicinesaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android_wavelength.medicinesaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}