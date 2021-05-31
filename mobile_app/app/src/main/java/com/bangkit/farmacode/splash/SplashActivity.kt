package com.bangkit.farmacode.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bangkit.farmacode.databinding.ActivitySplashBinding
import com.bangkit.farmacode.onboarding.OnBoardingActivity
import com.bangkit.farmacode.scanner.ScannerActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            hide()
        }

        Handler(mainLooper).postDelayed({
            if (onBoardingFinished()) {
                val intent = Intent(this, ScannerActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, OnBoardingActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPreferences = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("Finished", false)
    }
}