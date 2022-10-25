package com.riseup.riseup_bussiness.view

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.riseup.riseup_bussiness.databinding.ReportBugBinding

class BugReportDialog(

    private val onSubmitClickListener: (String) -> Unit

) : DialogFragment() {

    private lateinit var binding : ReportBugBinding

    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {
        binding = ReportBugBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.sendBugReportBtn.setOnClickListener {
            onSubmitClickListener.invoke(binding.editTextTextMultiLineBugReport.text.toString())
            dismiss()
        }

        binding.omitirBugImgReport.setOnClickListener {
            dismiss()
        }
        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }



}