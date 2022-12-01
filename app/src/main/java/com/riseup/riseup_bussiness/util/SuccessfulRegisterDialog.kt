package com.riseup.riseup_bussiness.util

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.riseup.riseup_bussiness.databinding.SuccesfullRegisterDialogBinding


class SuccessfulRegisterDialog:DialogFragment() {

    private lateinit var binding: SuccesfullRegisterDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = SuccesfullRegisterDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setGravity(Gravity.CENTER)
        dialog.setCanceledOnTouchOutside(false)
        binding.confirmSuccesfulRegisterBtn.setOnClickListener {
            Log.e(">>>","Llegue al listener del boton confirm")
           // startActivity(Intent(requireContext(), MenuActivity::class.java))
            dialog.dismiss()
        }
        return dialog
    }
}