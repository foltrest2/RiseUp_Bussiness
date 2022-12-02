package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.activity.viewModels
import com.google.gson.Gson
import com.riseup.riseup_bussiness.databinding.ActivityUpdateAppearanceBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.viewmodel.ConfigUpdateAppearanceViewModel

class ConfigUpdateAppearanceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateAppearanceBinding
    private val viewModel : ConfigUpdateAppearanceViewModel by viewModels()
    private lateinit var galleryLauncherBanner : ActivityResultLauncher<Intent>
    private lateinit var galleryLauncherCard : ActivityResultLauncher<Intent>
    private lateinit var disco : DiscoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAppearanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        disco = loadDisco()!!

        galleryLauncherBanner = registerForActivityResult(StartActivityForResult(), ::onGalleryResultBanner)
        galleryLauncherCard = registerForActivityResult(StartActivityForResult(), ::onGalleryResultCard)

        viewModel.disco = disco
        //viewModel.loadBannersURL()

        binding.backArrowAppeareanceConfigBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigUpdateAppearanceActivity, ConfigurationActivity::class.java))
        }

        binding.bannerSetBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncherBanner.launch(intent)
        }

        binding.cardSetBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncherCard.launch(intent)
        }

        binding.DiscoPreviewTV.setOnClickListener {
            startActivity(Intent(this@ConfigUpdateAppearanceActivity, DiscoPreviewActivity::class.java))
        }

        viewModel.incomingBanner.observe(this){
            disco.bannerURL = it
            saveUserSp(disco)
        }

        viewModel.incomingBannerCard.observe(this){
            disco.bannerCardURL = it
            saveUserSp(disco)
        }

    }

    private fun onGalleryResultBanner(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            Toast.makeText(this,"Imagen actualizada",Toast.LENGTH_SHORT).show()
            //binding.imagePrueba.setImageURI(uriImage)
            viewModel.uploadMainBanner(uriImage!!)
        }
    }

    private fun onGalleryResultCard(result: ActivityResult){
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            Toast.makeText(this,"Imagen actualizada",Toast.LENGTH_SHORT).show()
            //binding.imagePrueba.setImageURI(uriImage)
            viewModel.uploadcardBanner(uriImage!!)
        }
    }

    private fun loadDisco(): DiscoModel? {
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, DiscoModel::class.java)
        }
    }

    private fun saveUserSp(user: DiscoModel) {
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario", json).apply()
    }


}