package com.example.mealmate.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.mealmate.databinding.FragmentMealmateRegisterBinding
import com.example.mealmate.R
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.data.api.response.RegisterResponse
import com.example.mealmate.ui.dashboard.MealMateMainActivity
import com.example.mealmate.utils.SessionManager
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class RegisterFragment: Fragment() {

    private lateinit var binding: FragmentMealmateRegisterBinding
    private val viewModel: LoginViewModel by viewModels()

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

        viewModel.registerResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processRegister(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

        binding.apply {
            btnContinue.setOnClickListener {
                doSignup()
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
    fun doSignup() {
        val email=binding.edtEmail.text.toString()
        val username=binding.edtUsername.text.toString()
        val password=binding.edtPassword.text.toString()
        val phone=binding.edtPhone.text.toString()
        viewModel.registerUser(username=username,email=email,password=password,phone=phone)
    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }
    fun processRegister(data: RegisterResponse?) {
        showToast("Success:" + data?.message)
        navigateToSignin()
    }
    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }
    fun showToast(msg: String) {
        Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    private fun navigateToSignin() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.host_login_activity, LoginFragment())
            .addToBackStack(null)
            .commit()
    }
}



