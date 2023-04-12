package com.example.mealmate.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.databinding.FragmentMealmateRegisterBinding
import com.example.mealmate.R

class RegisterFragment: Fragment() {

    private lateinit var binding: FragmentMealmateRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnContinue.setOnClickListener {
                //setCurrentFragment(NftVerifyFragment())
                print("btnContinue")
            }

            tvSignIn.setOnClickListener {
                setCurrentFragment(LoginFragment())
            }

            btnBack.setOnClickListener {
                if (parentFragmentManager.backStackEntryCount == 0) {
                    requireActivity().onBackPressed()
                } else {
                    requireActivity().supportFragmentManager.popBackStackImmediate()
                }
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.host_login_activity, fragment)
        }
    }
}