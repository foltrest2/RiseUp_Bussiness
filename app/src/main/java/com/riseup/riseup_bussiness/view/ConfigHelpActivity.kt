package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.riseup.riseup_bussiness.databinding.ActivityConfigHelpBinding

class ConfigHelpActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.atrasArrowHelpBtn.setOnClickListener {
            startActivity(Intent(this@ConfigHelpActivity, ConfigurationActivity::class.java))
        }

        binding.soporteYpreguntasFreqConstraint.setOnClickListener {
            startActivity(Intent(this@ConfigHelpActivity, ConfigHelpCenterActivity::class.java))
        }

        binding.reportarProbConstraint.setOnClickListener {
            BugReportDialog(
                onSubmitClickListener = { report ->
                    Toast.makeText(this@ConfigHelpActivity,"Reporte: ${report}", Toast.LENGTH_LONG).show()
                }
            ).show(supportFragmentManager, "dialog")
        }
    }
}