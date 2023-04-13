package com.example.mealmate.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.mealmate.R
import com.example.mealmate.databinding.OnboardingBinding
import com.example.mealmate.model.MealMateOnboarding
import com.example.mealmate.ui.dashboard.MealMateMainActivity
import com.example.mealmate.ui.login.Login
import com.example.mealmate.utils.SessionManager

class MealMateOnboarding: AppCompatActivity()  {
    private lateinit var binding: OnboardingBinding

    private val onBoardingViewPagerAdapter = OnboardingAdapter(
        listOf(
            MealMateOnboarding(
                        "Welcome to MealMate",
                " Your Personal Meal Matching App"
            ),
            MealMateOnboarding(
                "Finding your next MealMate\n" +
                        "Was never that easy",
                "Discover Your Perfect Meal Match and Simplify Your Food Choices "
            )
        )
    )

    private val indicators = arrayOfNulls<ImageView>(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val token = SessionManager.getToken(this)
        if (!token.isNullOrBlank()) {
            navigateToHome()
        }
        setupOnboardingIndicator()
        setCurrentIndicator(0)
        setViewpagerAdapter()

        binding.apply {
            btnGetStarted.setOnClickListener {
                val intent = Intent(this@MealMateOnboarding, Login::class.java)
                intent.putExtra("SIGN_METHOD","sign_up")
                startActivity(intent)
            }

            tvSignIn.setOnClickListener {
                val intent = Intent(this@MealMateOnboarding, Login::class.java)
                intent.putExtra("SIGN_METHOD","sign_in")
                startActivity(intent)
            }
        }
    }
    private fun setupOnboardingIndicator() {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_mealmate_onboarding_indicator_inactive
                )
            )
            indicators[i]!!.layoutParams = layoutParams
            binding.layoutOnboardingIndicators.addView(indicators[i])
        }
    }

    private fun setViewpagerAdapter() {

        binding.onBoardingViewPager.adapter = onBoardingViewPagerAdapter

        binding.onBoardingViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicator(position)
                }

            }
        )

    }

    private fun setCurrentIndicator(index: Int) {

        for (i in indicators.indices) {
            val imageView: ImageView = binding.layoutOnboardingIndicators.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_mealmate_onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_mealmate_onboarding_indicator_inactive
                    )
                )
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MealMateMainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}