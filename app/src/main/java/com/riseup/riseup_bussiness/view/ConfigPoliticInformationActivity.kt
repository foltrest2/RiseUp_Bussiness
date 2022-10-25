package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_bussiness.databinding.ActivityConfigPoliticInformationBinding

class ConfigPoliticInformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConfigPoliticInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigPoliticInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backArrowPoliticInfoBtn.setOnClickListener {
            startActivity(Intent(this@ConfigPoliticInformationActivity, ConfigInformationActivity::class.java))
        }
    }
}