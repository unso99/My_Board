package com.myboard.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myboard.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInputBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}