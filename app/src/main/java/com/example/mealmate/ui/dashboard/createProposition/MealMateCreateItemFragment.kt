package com.example.mealmate.ui.dashboard.createProposition

import android.content.Context
import android.content.SharedPreferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.mealmate.R
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.FindEmailResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.data.api.response.PropositionResponse
import com.example.mealmate.databinding.FragmentMealmateCreateItemBinding
import com.example.mealmate.ui.dashboard.createProposition.createsuccess.MealMateCreateItemSuccessFragment
import com.example.mealmate.ui.dashboard.createProposition.preview.MealMatePreviewFragment
import com.example.mealmate.ui.login.forget_password.VerifyFragment
import com.example.mealmate.utils.SessionManager
import com.example.mealmate.viewmodel.PropositionViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MealMateCreateItemFragment: Fragment()  {

    private lateinit var binding: FragmentMealmateCreateItemBinding
    private val viewModel: PropositionViewModel by viewModels()

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
        viewModel.propostionResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    navigateToCreateItemSuccess()
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }
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

                addProposition()
            }

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
                btmNavbar.visibility = View.VISIBLE
                fab.visibility = View.VISIBLE
            }
            edtName.setText(title)
        }
    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    fun processPropositionSuccess(data: PropositionResponse?) {
        showToast("Success:" + data?.success)
        navigateToCreateItemSuccess()
    }
    fun addProposition() {
        val restaurantName = binding.edtSearch.text.toString()
        val date = binding.edtRoyalty.text.toString()
        val restaurantAddress = binding.edtSalesEnd.text.toString()
        val sharedPref : SharedPreferences =
            requireContext().getSharedPreferences(context?.getString(R.string.app_name), Context.MODE_PRIVATE)
        val id = sharedPref.getString("_id", "id")
        System.out.println(id)
        if (id != null) {
            viewModel.AddProposition(restauName = restaurantName, date =date,address=restaurantAddress, owner = id )
        }

    }
    private fun navigateToCreateItemSuccess() {
        requireActivity().supportFragmentManager.commit {
                 addToBackStack(null)
                 replace(R.id.host_fragment_activity_main, MealMateCreateItemSuccessFragment())
             }
    }
}