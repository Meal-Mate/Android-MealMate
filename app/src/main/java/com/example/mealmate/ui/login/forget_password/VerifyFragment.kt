package com.example.mealmate.ui.login.forget_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.example.mealmate.R
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.RecoverPasswordResponse
import com.example.mealmate.databinding.FragmentMealmateVerifyBinding
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentMealmateVerifyBinding
    private val viewModel: LoginViewModel by viewModels()
    private var email: String? = null


    companion object {
        private val TAG = VerifyFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealmateVerifyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        print(email)

        viewModel.recoverResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processRecover(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }


            binding.btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStackImmediate()
            }

            binding.rbEmail.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.rbPhone.isChecked = false
                    email(true)
                    phone(false)
                }
            }

            binding.rbPhone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    binding.rbEmail.isChecked = false
                    phone(true)
                    email(false)
                }
            }

            binding.btnContinue.setOnClickListener {
                print("recovering")
                doRecover()
            }


    }

    private fun phone(state: Boolean) {
        if (state){
            binding.apply {
                ivPhone.setBackgroundResource(R.drawable.ic_mealmate_phone_active)
                linearPhone.setBackgroundResource(R.drawable.bg_mealmate_outline_primary)
            }
        } else {
            binding.apply {
                ivPhone.setBackgroundResource(R.drawable.ic_mealmate_phone_inactive)
                linearPhone.setBackgroundResource(R.drawable.bg_mealmate_outline_secondary)
            }
        }
    }

    private fun email(state: Boolean) {
        if (state){
            binding.apply {
                ivEmail.setBackgroundResource(R.drawable.ic_mealmate_email_active)
                linearEmail.setBackgroundResource(R.drawable.bg_mealmate_outline_primary)
            }
        } else {
            binding.apply {
                ivEmail.setBackgroundResource(R.drawable.ic_mealmate_email_inactive)
                linearEmail.setBackgroundResource(R.drawable.bg_mealmate_outline_secondary)
            }
        }
    }

    fun doRecover() {
        this.email?.let { email ->
            println("Email is $email")
            viewModel.recoverPassword(email = email)
        }
    }


    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }
    fun processRecover(data: RecoverPasswordResponse?) {
        showToast("Success:" + data?.message)
        navigateToSetPin()
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    private fun navigateToSetPin() {
        parentFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.host_login_activity, SetPinFragment())
        }
    }
    fun setEmail(email: String) {
        this.email = email
    }

}