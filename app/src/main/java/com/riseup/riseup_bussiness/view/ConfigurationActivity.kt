package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.riseup.riseup_bussiness.databinding.ActivityConfigurationBinding

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.contraintAparienciaGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigUpdateAppearanceActivity::class.java))
        }
        binding.contraintIdiomaGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigLanguageActivity::class.java))
        }
        binding.contraintInfoAppGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigInformationActivity::class.java))
        }
        binding.contraintHelpAppGo.setOnClickListener{
            startActivity(Intent(this@ConfigurationActivity, ConfigHelpActivity::class.java))
        }
        binding.borrarCacheTVProfile.setOnClickListener {
            CacheCleanedDialog().show(supportFragmentManager, "dialogcache")
        }
    }

}