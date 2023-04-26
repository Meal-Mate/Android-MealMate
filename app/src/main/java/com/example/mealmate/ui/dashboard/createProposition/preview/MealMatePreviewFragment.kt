package com.example.mealmate.ui.dashboard.createProposition.preview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmatePreviewBinding
import com.example.mealmate.utils.GlideHelper.loadImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMatePreviewFragment : Fragment() {
    private lateinit var binding: FragmentMealmatePreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmatePreviewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btmNavbar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab_add)
        btmNavbar.visibility = View.GONE
        fab.visibility = View.GONE

        binding.apply {
            ivItem.loadImage("https://picsum.photos/481/480")
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
                btmNavbar.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
            }
        }
    }
}