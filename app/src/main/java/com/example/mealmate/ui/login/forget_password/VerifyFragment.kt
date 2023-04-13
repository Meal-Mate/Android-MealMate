package com.aksantaradigital.aksaone.nft.ui.login.register.verify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.aksantaradigital.aksaone.nft.R
import com.aksantaradigital.aksaone.nft.databinding.FragmentNftVerifyBinding
import com.aksantaradigital.aksaone.nft.ui.login.register.verify.setpin.NftSetPinFragment

class NftVerifyFragment : Fragment() {

    private lateinit var binding: FragmentNftVerifyBinding

    companion object {
        private val TAG = NftVerifyFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNftVerifyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStackImmediate()
            }

            rbEmail.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    rbPhone.isChecked = false
                    email(true)
                    phone(false)
                }
            }

            rbPhone.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    rbEmail.isChecked = false
                    phone(true)
                    email(false)
                }
            }

            btnContinue.setOnClickListener {
                setCurrentFragment(NftSetPinFragment())
            }

        }
    }

    private fun phone(state: Boolean) {
        if (state){
            binding.apply {
                ivPhone.setBackgroundResource(R.drawable.ic_nft_phone_active)
                linearPhone.setBackgroundResource(R.drawable.bg_nft_outline_primary)
            }
        } else {
            binding.apply {
                ivPhone.setBackgroundResource(R.drawable.ic_nft_phone_inactive)
                linearPhone.setBackgroundResource(R.drawable.bg_nft_outline_secondary)
            }
        }
    }

    private fun email(state: Boolean) {
        if (state){
            binding.apply {
                ivEmail.setBackgroundResource(R.drawable.ic_nft_email_active)
                linearEmail.setBackgroundResource(R.drawable.bg_nft_outline_primary)
            }
        } else {
            binding.apply {
                ivEmail.setBackgroundResource(R.drawable.ic_nft_email_inactive)
                linearEmail.setBackgroundResource(R.drawable.bg_nft_outline_secondary)
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