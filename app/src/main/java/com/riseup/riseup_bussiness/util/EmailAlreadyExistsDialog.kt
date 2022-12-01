package com.riseup.riseup_bussiness.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.riseup.riseup_bussiness.databinding.EmailAlreadyExistDialogBinding

class EmailAlreadyExistsDialog:DialogFragment() {
    private lateinit var binding: EmailAlreadyExistDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = EmailAlreadyExistDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        binding.confirmEmailAEDialogBtn.setOnClickListener {
            dialog.dismiss()
        }
        return dialog
    }
}