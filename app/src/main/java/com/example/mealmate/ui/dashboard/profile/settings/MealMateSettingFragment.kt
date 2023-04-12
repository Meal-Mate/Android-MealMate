package com.example.mealmate.ui.dashboard.profile.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateSettingBinding
import com.example.mealmate.ui.dashboard.profile.settings.editprofile.MealMateEditPofileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMateSettingFragment : Fragment() {
    private lateinit var binding: FragmentMealmateSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btmNavbar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab_add)
        btmNavbar.visibility = View.GONE
        fab.visibility = View.GONE

        binding.apply {
            btnEditProfile.setOnClickListener {
                requireActivity().supportFragmentManager.commit {
                    replace(R.id.host_fragment_activity_main, MealMateEditPofileFragment())
                    addToBackStack(null)
                }
            }

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
                btmNavbar.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
            }

            btnLogout.setOnClickListener {
                requireActivity().finish()
            }

        }

        darkModeSettingView()
    }

    private fun darkModeSettingView() {

        val sharedPreferences = requireActivity().getSharedPreferences("AKSTY", Context.MODE_PRIVATE)
        val mode = sharedPreferences.getInt("light_dark_mode", AppCompatDelegate.MODE_NIGHT_NO)
        AppCompatDelegate.setDefaultNightMode(mode)

        binding.apply {
/*
           if (mode == AppCompatDelegate.MODE_NIGHT_NO) {
                linearLightMode.background = resources.getDrawable(R.drawable.bg_nft_light_dark_mode, null)
                ivLightMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                tvLightMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                linearDarkMode.background = resources.getDrawable(android.R.color.transparent, null)
                ivDarkMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))
                tvDarkMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))
            } else {
                linearDarkMode.background = resources.getDrawable(R.drawable.bg_nft_light_dark_mode, null)
                ivDarkMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                tvDarkMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                linearLightMode.background = resources.getDrawable(android.R.color.transparent, null)
                ivLightMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))
                tvLightMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))
            }

            linearLightMode.setOnClickListener {
                it.background = resources.getDrawable(R.drawable.bg_nft_light_dark_mode, null)
                ivLightMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                tvLightMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                linearDarkMode.background = resources.getDrawable(android.R.color.transparent, null)
                ivDarkMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))
                tvDarkMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))

                sharedPreferences.edit().putInt("light_dark_mode", AppCompatDelegate.MODE_NIGHT_NO).commit()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                requireActivity().onBackPressed()
            }

            linearDarkMode.setOnClickListener {
                it.background = resources.getDrawable(R.drawable.bg_nft_light_dark_mode, null)
                ivDarkMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                tvDarkMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                linearLightMode.background = resources.getDrawable(android.R.color.transparent, null)
                ivLightMode.setColorFilter(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))
                tvLightMode.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorNeutral300))

                sharedPreferences.edit().putInt("light_dark_mode", AppCompatDelegate.MODE_NIGHT_YES).commit()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                requireActivity().onBackPressed()
            }*/
        }
    }
}