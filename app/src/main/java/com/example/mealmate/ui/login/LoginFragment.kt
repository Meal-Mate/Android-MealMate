package com.example.mealmate.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mealmate.databinding.FragmentMealmateLoginBinding
import com.example.mealmate.R
import androidx.fragment.app.viewModels
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.FindEmailResponse
import com.example.mealmate.data.api.response.LoginResponse
import com.example.mealmate.ui.dashboard.MealMateMainActivity
import com.example.mealmate.ui.login.forget_password.VerifyFragment
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
        viewModel.findbyemailResult.observe(this.viewLifecycleOwner){
            when (it){
                is BaseResponse.Loading->{
                    showLoading()
                }
                is BaseResponse.Success->{
                    stopLoading()
                    processFindByEmail(it.data)
                }

                is BaseResponse.Error ->{
                    processError(it.msg)
                }
                else ->{
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

            binding.tvForgotPassword.setOnClickListener{
                val email = binding.edtEmail.text.toString()
                if(email== ""){
                    showToast("You should Enter your Email Adress or Phone Number first !!")
                }
                else{
                    findByEmail(email=email)
                }
            }




    }
    fun doLogin() {
        val email = binding.edtEmail.text.toString()
        val pwd = binding.edtPassword.text.toString()
        viewModel.loginUser(email = email, pwd = pwd)

    }
    fun findByEmail(email:String){
        val email = binding.edtEmail.text.toString()
        viewModel.findByEmail(email=email)
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
            val sharedPref : SharedPreferences =
                requireContext().getSharedPreferences(context?.getString(R.string.app_name), Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("Username", data?.data?.username)
            editor.putString("email", data?.data?.email)
            editor.putString("phone", data?.data?.phone)
            editor.apply()
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
    fun processFindByEmail(data: FindEmailResponse?) {
        showToast("Success:" + data?.success)
        println(data?.data?.phone.toString())
        navigateToVerifyEmail(email=data?.data?.email.toString(),phone=data?.data?.phone.toString(),username=data?.data?.username.toString())

    }
    private fun navigateToVerifyEmail(email: String, phone: String, username: String) {
        val bundle = Bundle().apply {
            putString("email", email)
            putString("phone", phone)
            putString("username", username)
        }
        val verifyFragment = VerifyFragment().apply {
            arguments = bundle
        }
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.host_login_activity, verifyFragment)
            .addToBackStack(null)
            .commit()
    }

}