package com.example.mealmate.ui.login.forget_password

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
import com.example.mealmate.databinding.FragmentMealmateEditPasswordBinding
import com.example.mealmate.databinding.FragmentMealmateEditPofileBinding
import com.example.mealmate.databinding.FragmentMealmateProfileBinding
import com.example.mealmate.databinding.FragmentMealmateSettingBinding
import com.example.mealmate.ui.dashboard.profile.settings.MealMateSettingFragment
import com.example.mealmate.utils.GlideHelper.loadImage
import com.google.android.material.tabs.TabLayoutMediator

class EditPaswwordFragment : Fragment(){
    private lateinit var binding: FragmentMealmateEditPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateEditPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ivAvatar.loadImage("https://picsum.photos/401/400")
            binding.btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
}