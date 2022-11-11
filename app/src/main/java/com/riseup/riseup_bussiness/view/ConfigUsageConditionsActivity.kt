package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_bussiness.databinding.ActivityConfigUsageConditionsBinding

class ConfigUsageConditionsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConfigUsageConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigUsageConditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backUsageConditionsBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigUsageConditionsActivity, ConfigInformationActivity::class.java))
        }

    }
}