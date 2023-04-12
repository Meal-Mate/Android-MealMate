package com.example.mealmate.ui.login

import  android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.LoginBinding
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class Login: AppCompatActivity() {
    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentData = intent.getStringExtra("SIGN_METHOD")

        val registerFragment = RegisterFragment()
        val loginFragment = LoginFragment()

        if (intentData == "sign_up") {
            supportFragmentManager.commit {
                replace(R.id.host_login_activity, registerFragment)
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.host_login_activity, loginFragment)
            }
        }

    }
}