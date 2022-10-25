package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_bussiness.databinding.ActivityConfigLanguageBinding

class ConfigLanguageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConfigLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.backArrowLanguageConfigBtn.setOnClickListener {
            startActivity(Intent(this@ConfigLanguageActivity, ConfigurationActivity::class.java))
        }
    }
}