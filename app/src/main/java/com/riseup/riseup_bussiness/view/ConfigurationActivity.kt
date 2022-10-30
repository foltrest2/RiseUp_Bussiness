package com.riseup.riseup_bussiness.view

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.riseup.riseup_bussiness.databinding.ActivityConfigurationBinding

class ConfigurationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigurationBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigurationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestPermissions(arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
        ),1)


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
        binding.contraintUserPagosGo.setOnClickListener {
            startActivity(Intent(this@ConfigurationActivity, OrdersListActivity::class.java))
        }
    }

}