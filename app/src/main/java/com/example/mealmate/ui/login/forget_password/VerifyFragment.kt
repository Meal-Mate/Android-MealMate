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
import com.example.mealmate.utils.SessionManager
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class VerifyFragment : Fragment() {

    private lateinit var binding: FragmentMealmateVerifyBinding
    private val viewModel: LoginViewModel by viewModels()


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
        val intent = activity?.intent

        // Retrieve the values of the email, phone, and username variables
        val email=arguments?.getString("email")
        val phone=arguments?.getString("phone")
        val username=arguments?.getString("username")
        val hiddenDigits = phone?.substring(0, phone.length - 3)?.length ?: 0 // number of hidden digits
        val maskedPhone = "*".repeat(hiddenDigits) + phone?.substring(phone.length - 3) // masked phone number

        binding.txtEmail.setText(email)
        binding.txtPhone.setText(maskedPhone)


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
                doRecover(email)
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

    fun doRecover(email: String?) {
        if (email != null) {
            viewModel.recoverPassword(email = email)
        }
        else{
            print("Email Null !!!")
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

}