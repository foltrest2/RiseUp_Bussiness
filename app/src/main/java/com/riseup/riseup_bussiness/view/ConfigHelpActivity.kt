package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.riseup.riseup_bussiness.databinding.ActivityConfigHelpBinding
import com.riseup.riseup_bussiness.viewmodel.ConfigHelpViewModel

class ConfigHelpActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigHelpBinding
    private val viewModel : ConfigHelpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.atrasArrowHelpBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigHelpActivity, ConfigurationActivity::class.java))
        }

        binding.soporteYpreguntasFreqConstraint.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigHelpActivity, ConfigHelpCenterActivity::class.java))
        }

        binding.reportarProbConstraint.setOnClickListener {
            BugReportDialog(
                onSubmitClickListener = { report ->
                    if(report.isNotEmpty()){
                        viewModel.sendReport(report)
                        Toast.makeText(this,"Gracias por su reporte", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,"Porfavor especifique la falla", Toast.LENGTH_LONG).show()
                    }
                }
            ).show(supportFragmentManager, "dialog")
        }
    }
}