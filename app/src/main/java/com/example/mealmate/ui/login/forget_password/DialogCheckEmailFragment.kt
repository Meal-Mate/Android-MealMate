package com.aksantaradigital.aksaone.nft.ui.login.register.verify

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.aksantaradigital.aksaone.nft.databinding.FragmentDialogCheckEmailBinding

class DialogCheckEmailFragment: DialogFragment() {

    private lateinit var binding: FragmentDialogCheckEmailBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        binding = FragmentDialogCheckEmailBinding.inflate(layoutInflater)
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

}