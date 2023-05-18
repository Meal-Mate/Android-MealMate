package com.example.mealmate.ui.dashboard.createProposition


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentMealmateCreateItemBinding
import com.example.mealmate.ui.dashboard.createProposition.createsuccess.MealMateCreateItemSuccessFragment
import com.example.mealmate.ui.dashboard.createProposition.preview.MealMatePreviewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMateCreateItemFragment: Fragment()  {

    private lateinit var binding: FragmentMealmateCreateItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateCreateItemBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btmNavbar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        val fab: FloatingActionButton = requireActivity().findViewById(R.id.fab_add)
        btmNavbar.visibility = View.GONE
        fab.visibility = View.GONE
        val title=arguments?.getString("restaurant_title")
        println(title)

        binding.apply {
            btnPreview.setOnClickListener {

                requireActivity().supportFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.host_fragment_activity_main, MealMatePreviewFragment())
                }
            }
            btnCreateItem.setOnClickListener {
                requireActivity().supportFragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.host_fragment_activity_main, MealMateCreateItemSuccessFragment())
                }
            }

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
                btmNavbar.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
            }
            edtName.setText(title)
        }
    }


}