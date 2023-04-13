package com.example.mealmate.ui.dashboard.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateProfileBinding
import com.example.mealmate.ui.dashboard.profile.settings.MealMateSettingFragment
import com.example.mealmate.utils.GlideHelper.loadImage

class MealMateProfileFragment: Fragment() {
    private lateinit var binding: FragmentMealmateProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref : SharedPreferences =
            requireContext().getSharedPreferences(context?.getString(R.string.app_name), Context.MODE_PRIVATE)
        val username = sharedPref.getString("Username", "username")
        val email = sharedPref.getString("email", "email")

        binding.apply {
            tvUsername.setText(username)
            tvIdUser.setText(email)
            ivAvatar.loadImage("https://picsum.photos/401/400")
            linearLayoutCopy.setOnClickListener {
                Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
            }

            btnSetting.setOnClickListener {
                requireActivity().supportFragmentManager.commit {
                    replace(R.id.host_fragment_activity_main, MealMateSettingFragment())
                        .addToBackStack(null)
                }
            }
        }

        //connect tabLayout with viewPager2
    }
}