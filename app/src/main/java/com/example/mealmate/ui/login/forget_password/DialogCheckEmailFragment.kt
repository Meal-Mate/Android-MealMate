package com.example.mealmate.ui.login.forget_password

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mealmate.databinding.FragmentCheckEmailBinding

class DialogCheckEmailFragment: DialogFragment() {

    private lateinit var binding: FragmentCheckEmailBinding
    private val dialogDismissDelay = 3 * 60 * 1000L // 3 minutes in milliseconds

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        binding = FragmentCheckEmailBinding.inflate(layoutInflater)
        builder.setView(binding.root)
        return builder.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val handler = Handler()
        handler.postDelayed({ dismiss() }, dialogDismissDelay)
    }
}
