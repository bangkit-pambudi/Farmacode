package com.bangkit.farmacode.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bangkit.farmacode.databinding.ActivityOnBoardingBinding
import com.bangkit.farmacode.onboarding.fragments.OnBoardingFragment1
import com.bangkit.farmacode.onboarding.fragments.OnBoardingFragment2
import com.bangkit.farmacode.onboarding.fragments.OnBoardingFragment3

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            hide()
        }

        val fragmentList = arrayListOf<Fragment>(
            OnBoardingFragment1(),
            OnBoardingFragment2(),
            OnBoardingFragment3()
        )

        val adapter = OnBoardingAdapter(fragmentList, supportFragmentManager, lifecycle)

        binding.boardingViewPager.adapter = adapter
    }
}