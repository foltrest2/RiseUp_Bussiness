package com.riseup.riseup_bussiness.view

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.riseup.riseup_bussiness.databinding.CacheCleanedBinding

class CacheCleanedDialog : DialogFragment() {

    private lateinit var binding : CacheCleanedBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CacheCleanedBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }




}