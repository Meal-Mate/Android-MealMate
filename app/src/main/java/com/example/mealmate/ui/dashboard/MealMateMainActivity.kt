package com.example.mealmate.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.ActivityMealmateMainBinding
import com.example.mealmate.ui.dashboard.home.MealMateHomeFragement
import com.example.mealmate.ui.dashboard.profile.MealMateProfileFragment


class MealMateMainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMealmateMainBinding
    private lateinit var profileFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMealmateMainBinding.inflate(layoutInflater)

        binding.bottomNavigation.itemIconTintList = null
        setContentView(binding.root)

        if (savedInstanceState != null) {
            profileFragment = supportFragmentManager.getFragment(savedInstanceState, "profileFragment")!!
            try {
                setCurrentFragment(profileFragment)
            } catch (_: Exception) {

            }
        }

        initView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "profileFragment", profileFragment)
    }

    private fun initView() {
        binding.apply {

                val profileFragment = MealMateProfileFragment()
                val homeFragment = MealMateHomeFragement()
                 setCurrentFragment(homeFragment)

            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        setCurrentFragment(homeFragment)
                    }
                    R.id.explore -> {

                    }
                    R.id.statistic -> {

                    }
                    R.id.profile -> {
                        setCurrentFragment(profileFragment)
                    }
                }
                true
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        profileFragment = fragment
        supportFragmentManager.commit {
            replace(R.id.host_fragment_activity_main, fragment)
        }
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    override fun onBackPressed() {

        when (supportFragmentManager.backStackEntryCount) {
            0 -> {
                super.onBackPressed()
                //additional code
            }
            1 -> {
                supportFragmentManager.popBackStack()
                binding.bottomNavigation.visibility = View.VISIBLE
                binding.fabAdd.visibility = View.VISIBLE
            }
            else -> {
                supportFragmentManager.popBackStack()
            }
        }

    }
}