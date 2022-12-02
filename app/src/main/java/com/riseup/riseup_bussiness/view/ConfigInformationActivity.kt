package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.riseup.riseup_bussiness.databinding.ActivityConfigInformationBinding

class ConfigInformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.atrasArrowInfoAppBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigInformationActivity, ConfigurationActivity::class.java))
        }
        binding.politicaPrivConstraint.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigInformationActivity, ConfigPoliticInformationActivity::class.java))
        }
        binding.condUsoConstraint.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigInformationActivity, ConfigUsageConditionsActivity::class.java))
        }
        binding.bibCodAbirConstraint.setOnClickListener {
            Toast.makeText(this@ConfigInformationActivity,"Información no disponible", Toast.LENGTH_SHORT).show()
        }



    }
}