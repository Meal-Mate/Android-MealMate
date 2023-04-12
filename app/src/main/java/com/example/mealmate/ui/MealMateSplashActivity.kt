package com.example.mealmate.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.mealmate.databinding.SplashScreenBinding
import com.example.mealmate.R
import com.example.mealmate.ui.onboarding.MealMateOnboarding

class MealMateSplashActivity: AppCompatActivity() {
    private lateinit var binding: SplashScreenBinding
    private val timeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        setTheme(R.style.AppTheme)

        val sharedPreferences = getSharedPreferences("AKSTY", Context.MODE_PRIVATE)
        val mode = sharedPreferences.getInt("light_dark_mode", AppCompatDelegate.MODE_NIGHT_NO)
        Log.d("MainActivity", "onCreate: $mode")
        AppCompatDelegate.setDefaultNightMode(mode)

        setContentView(binding.root)

        initSplash()
    }
    private fun initSplash() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@MealMateSplashActivity, MealMateOnboarding::class.java)
            startActivity(intent)
            finish()
        }, timeOut)
    }
}