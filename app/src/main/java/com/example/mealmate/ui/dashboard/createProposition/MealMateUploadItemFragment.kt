package com.example.mealmate.ui.dashboard.createProposition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateUploadItemBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMateUploadItemFragment : Fragment(){
    private lateinit var binding: FragmentMealmateUploadItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateUploadItemBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = MapsFragment()
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frame_maps,fragment)
        transaction.commit()
        val btmNavbar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab_add)
        btmNavbar.visibility = View.GONE
        fab.visibility = View.GONE

        binding.apply {
            btnBack.setOnClickListener { requireActivity().onBackPressed() }

        }

    }
}