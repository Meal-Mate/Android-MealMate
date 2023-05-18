package com.example.mealmate.ui.dashboard

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.ActivityMealmateMainBinding
import com.example.mealmate.ui.dashboard.createProposition.MapsFragment
import com.example.mealmate.ui.dashboard.createProposition.MealMateUploadItemFragment
import com.example.mealmate.ui.dashboard.createProposition.preview.MealMatePreviewFragment
import com.example.mealmate.ui.dashboard.home.MealMateHomeFragement
import com.example.mealmate.ui.dashboard.profile.MealMateProfileFragment
import com.example.mealmate.ui.dashboard.proposition.MealMatePropositionFragment
import com.example.mealmate.ui.login.forget_password.VerifyFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MealMateMainActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMealmateMainBinding
    private lateinit var profileFragment: Fragment
    private val locationPermissionRequestCode = 101
    private val locationManager by lazy {
        this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

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
                val propositionFragment = MealMatePropositionFragment()
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        setCurrentFragment(homeFragment)
                    }
                    R.id.explore -> {

                    }
                    R.id.statistic -> {
                        setCurrentFragment(propositionFragment)
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
                    if (ContextCompat.checkSelfPermission(
                            this@MealMateMainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        requestLocation()
                    } else {
                        ActivityCompat.requestPermissions(
                            this@MealMateMainActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            locationPermissionRequestCode
                        )
                    }
                    supportFragmentManager.commit {
                        replace(R.id.host_fragment_activity_main, MealMateUploadItemFragment())
                        addToBackStack(null)
                    }

                    bottomSheet.dismiss()
                }

                btnMultiple?.setOnClickListener {
                    if (ContextCompat.checkSelfPermission(
                            this@MealMateMainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        requestLocation()
                    } else {
                        ActivityCompat.requestPermissions(
                            this@MealMateMainActivity,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            locationPermissionRequestCode
                        )
                    }

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
    private fun requestLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            location?.let {
                Toast.makeText(
                    this,
                    "Latitude: ${it.latitude}, Longitude: ${it.longitude}",
                    Toast.LENGTH_LONG
                ).show()
                val bundle = Bundle().apply {
                    putDouble("long", it.longitude)
                    putDouble("latt", it.latitude)
                }
                val mapsFragment = MapsFragment().apply {
                    arguments = bundle
                }
            } ?: run {
                Toast.makeText(
                    this,
                    "Unable to retrieve location",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionRequestCode
            )
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == locationPermissionRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestLocation()
            } else {
                Toast.makeText(
                    this,
                    "Location permission denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}