package com.example.mealmate.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mealmate.databinding.FragmentMealmateLoginBinding
import com.example.mealmate.R
import androidx.fragment.app.viewModels
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.ui.dashboard.MealMateMainActivity
import com.example.mealmate.ui.onboarding.MealMateOnboarding
import com.example.mealmate.utils.SessionManager
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class LoginFragment: Fragment() {
    private lateinit var binding: FragmentMealmateLoginBinding
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            binding.tvSignUp.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.host_login_activity, RegisterFragment())
                    .addToBackStack(null)
                    .commit()
            }
        viewModel.loginResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processLogin(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }

            binding.btnGetStarted.setOnClickListener {
                doLogin()
            }

            binding.btnBack.setOnClickListener {
                if (parentFragmentManager.backStackEntryCount == 0) {
                    requireActivity().onBackPressed()
                } else {
                    requireActivity().supportFragmentManager.popBackStackImmediate()
                }
            }



    }
    fun doLogin() {
        val email = binding.edtEmail.text.toString()
        val pwd = binding.edtPassword.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)

    }

    fun doSignup() {

    }

    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }
    fun processLogin(data: LoginResponse?) {
        showToast("Success:" + data?.message)
        if (!data?.token.isNullOrEmpty()) {
            data?.token?.let { SessionManager.saveAuthToken(this.requireContext(), it) }
            navigateToHome()
        }
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    private fun navigateToHome() {
        val intent =  Intent(requireContext(), MealMateMainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(intent)
    }
}