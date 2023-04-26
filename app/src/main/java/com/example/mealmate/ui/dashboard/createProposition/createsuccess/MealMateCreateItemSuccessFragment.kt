package com.example.mealmate.ui.dashboard.createProposition.createsuccess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateCreateItemSuccessBinding
import com.example.mealmate.utils.GlideHelper.loadImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMateCreateItemSuccessFragment : Fragment()  {
    private lateinit var binding: FragmentMealmateCreateItemSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMealmateCreateItemSuccessBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btmNavbar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab_add)
        btmNavbar.visibility = View.GONE
        fab.visibility = View.GONE

        binding.apply {

            layoutItem.apply {
                linearEndIn.visibility = View.GONE
                ivItem.loadImage("https://picsum.photos/481/480")
                btnPlaceBid.visibility = View.GONE
            }

            btnViewItem.setOnClickListener {
                when (requireActivity().supportFragmentManager.backStackEntryCount) {
                    3 -> {
                        requireActivity().supportFragmentManager.popBackStack()
                        requireActivity().supportFragmentManager.popBackStack()
                        requireActivity().supportFragmentManager.popBackStack()
                        btmNavbar.visibility = View.VISIBLE
                        fab.visibility = View.VISIBLE
                    }
                    2 -> {
                        requireActivity().supportFragmentManager.popBackStack()
                        requireActivity().supportFragmentManager.popBackStack()
                        btmNavbar.visibility = View.VISIBLE
                        fab.visibility = View.VISIBLE
                    }
                    else -> {
                        requireActivity().supportFragmentManager.popBackStack()
                        btmNavbar.visibility = View.VISIBLE
                        fab.visibility = View.VISIBLE

                    }
                }
            }
        }
    }
}