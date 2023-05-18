package com.example.mealmate.ui.login.interests

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentAddInterestsBinding
import com.example.mealmate.ui.dashboard.MealMateMainActivity
import com.example.mealmate.ui.login.RegisterFragment

class InterstsFragment : Fragment() {

    private lateinit var binding: FragmentAddInterestsBinding
    private var selectedButtonFood: TextView? = null
    private var selectedButtonCuisine: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddInterestsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            val intent =  Intent(requireContext(), MealMateMainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(intent)
        }
        binding.Chinese.setOnClickListener{
            selectedButtonCuisine ?.setTextColor(Color.BLACK)
            selectedButtonCuisine = binding.Chinese
            selectedButtonCuisine?.setTextColor(Color.GREEN)
        }

        binding.Italian.setOnClickListener{
            selectedButtonCuisine?.setTextColor(Color.BLACK)
            selectedButtonCuisine = binding.Italian
            selectedButtonCuisine?.setTextColor(Color.GREEN)
        }

        binding.Mexican.setOnClickListener{
            selectedButtonCuisine?.setTextColor(Color.BLACK)
            selectedButtonCuisine = binding.Mexican
            selectedButtonCuisine?.setTextColor(Color.GREEN)
        }

        binding.carnivorous.setOnClickListener{
            selectedButtonFood?.setTextColor(Color.BLACK)
            selectedButtonFood=binding.carnivorous
            selectedButtonFood?.setTextColor(Color.GREEN)
        }
        binding.cooking.setOnClickListener{
            binding.cooking.setTextColor(Color.GREEN)
        }
        binding.glutenFree.setOnClickListener{
            binding.glutenFree.setTextColor(Color.GREEN)
        }
        binding.vegetarian.setOnClickListener{
            binding.vegetarian.setTextColor(Color.GREEN)
        }
        binding.wineTasting.setOnClickListener{
            binding.wineTasting.setTextColor(Color.GREEN)
        }
        binding.Trying.setOnClickListener{
            binding.Trying.setTextColor(Color.GREEN)
        }

    }
}