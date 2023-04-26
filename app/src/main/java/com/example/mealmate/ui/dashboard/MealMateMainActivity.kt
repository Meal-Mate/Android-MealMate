package com.example.mealmate.ui.dashboard

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.ActivityMealmateMainBinding
import com.example.mealmate.ui.dashboard.createProposition.MealMateUploadItemFragment
import com.example.mealmate.ui.dashboard.createProposition.preview.MealMatePreviewFragment
import com.example.mealmate.ui.dashboard.home.MealMateHomeFragement
import com.example.mealmate.ui.dashboard.profile.MealMateProfileFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


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
            fabAdd.setOnClickListener {
                val bottomSheet = BottomSheetDialog(this@MealMateMainActivity, R.style.MealMateBottomSheet)
                bottomSheet.setContentView(R.layout.bottom_sheet_create_item)
                bottomSheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED

                val fabClose = bottomSheet.findViewById<FloatingActionButton>(R.id.fab_close_item)
                val btnSingle = bottomSheet.findViewById<LinearLayout>(R.id.btn_single_item)
                val btnMultiple = bottomSheet.findViewById<LinearLayout>(R.id.btn_multiple_item)

                fabClose?.setOnClickListener {
                    bottomSheet.dismiss()
                }

                btnSingle?.setOnClickListener {
                    supportFragmentManager.commit {
                        replace(R.id.host_fragment_activity_main, MealMateUploadItemFragment())
                        addToBackStack(null)
                    }

                    bottomSheet.dismiss()
                }

                btnMultiple?.setOnClickListener {
                    supportFragmentManager.commit {
                        replace(R.id.host_fragment_activity_main, MealMateUploadItemFragment())
                        addToBackStack(null)
                    }

                    bottomSheet.dismiss()
                }

                bottomSheet.behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {}

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        if (slideOffset < 0) {
                            fabClose?.visibility = View.GONE
                        } else {
                            fabClose?.visibility = View.VISIBLE
                        }
                    }

                })

                bottomSheet.show()
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