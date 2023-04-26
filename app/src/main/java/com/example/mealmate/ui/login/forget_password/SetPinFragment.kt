package com.example.mealmate.ui.login.forget_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.mealmate.R
import com.example.mealmate.databinding.FragmentSetPinBinding

class SetPinFragment: Fragment() {
    private lateinit var binding: FragmentSetPinBinding

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

        binding.apply {

            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStackImmediate()
            }

            btnContinue.setOnClickListener {
                //setCurrentFragment(())
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
