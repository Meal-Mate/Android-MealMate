package com.example.mealmate.ui.dashboard.proposition.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealmate.databinding.FragmentMealmateActivityBinding
import com.example.mealmate.model.MealmateActivity

class MealMateActivityFragment: Fragment() {
    private lateinit var binding: FragmentMealmateActivityBinding
    private lateinit var adapterActivity: MealMateActivityAdapter

    private val listActivity = listOf(
        MealmateActivity(
            "https://picsum.photos/55/48",
            "Hassen",
            "Cafe",
            "a minute ago",
            "Hassen",
            "Wachidun",
            "Pakpolici",
            "Hafood"
        ),MealmateActivity(
            "https://picsum.photos/45/58",
            "ala",
            "Dining",
            "a minute ago",
            "ala",
            "ala",
            "Pakpolici",
            "via mercato"
        ),MealmateActivity(
            "https://picsum.photos/65/48",
            "mouhamed",
            "Fine dining",
            "2 minutes ago",
            "mouhamed",
            "mouhamed",
            "Cemani",
            "xFood"
        ),
        MealmateActivity(
            "https://picsum.photos/46/48",
            "khaith",
            "Fine dining",
            "2 minutes ago",
            "khaith",
            "khaith",
            "Cemani",
            "kouzina"
        ),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateActivityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("AKSTY", Context.MODE_PRIVATE)
        val mode = sharedPreferences.getInt("light_dark_mode", AppCompatDelegate.MODE_NIGHT_NO)

        adapterActivity = MealMateActivityAdapter(listActivity, mode)

        binding.rvActivity.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterActivity
        }

    }
}