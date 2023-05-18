package com.example.mealmate.ui.login.forget_password

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.chaos.view.PinView
import com.example.mealmate.R
import com.example.mealmate.data.api.response.BaseResponse
import com.example.mealmate.data.api.response.RecoverPasswordResponse
import com.example.mealmate.data.api.response.ResetResponse
import com.example.mealmate.databinding.FragmentSetPinBinding
import com.harshita.retrofitlogin.viewmodel.LoginViewModel

class SetPinFragment: Fragment() {
    private lateinit var binding: FragmentSetPinBinding
    private val viewModel: LoginViewModel by viewModels()
    private val otp: String=""

    companion object {
        private val TAG = FragmentSetPinBinding::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSetPinBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        //dialog check email
        val dialogCheckEmailFragment = DialogCheckEmailFragment()
        dialogCheckEmailFragment.show(childFragmentManager, TAG)
        binding.otpView.setOtpCompletionListener{
            Log.d("Actual Value", it)
            doReset(it.toString())


        }

        viewModel.resetResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }

                is BaseResponse.Success -> {
                    stopLoading()
                    processReset(it.data)
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
        binding.btnContinue.setOnClickListener {
            Log.d("Actual Value", otp)


        }

    }

    fun doReset(otp: String) {
        if (otp != null) {
            println("$otp")
            viewModel.resetPassword(otp = otp)
        }
        else{
            print("Otp Null !!!")
        }
    }


    fun showLoading() {
        binding.prgbar.visibility = View.VISIBLE
    }

    fun stopLoading() {
        binding.prgbar.visibility = View.GONE
    }
    fun processReset(data: ResetResponse?) {
        showToast("Success:" + data?.message)
        navigateEditPassword()
    }

    fun processError(msg: String?) {
        showToast("Error:" + msg)
    }

    fun showToast(msg: String) {
        Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
    }
    private fun navigateEditPassword() {
        parentFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.host_login_activity, EditPaswwordFragment())
        }
    }

}
