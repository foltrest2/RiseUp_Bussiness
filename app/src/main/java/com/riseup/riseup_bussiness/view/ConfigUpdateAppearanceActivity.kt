package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.databinding.ActivityUpdateAppearanceBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.viewmodel.ConfigUpdateAppearanceViewModel
import java.util.*

class ConfigUpdateAppearanceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateAppearanceBinding
    private val viewModel : ConfigUpdateAppearanceViewModel by viewModels()

    private lateinit var galleryLauncher : ActivityResultLauncher<Intent>

    private lateinit var discoID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAppearanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        discoID = "1otzuoJuS4ZrQQH6REsL"

        galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)

        viewModel.discoID = discoID
        viewModel.updateImage()

        binding.backArrowAppeareanceConfigBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@ConfigUpdateAppearanceActivity, ConfigurationActivity::class.java))
        }

        binding.bannerSetBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)
        }

        viewModel.incomingBanner.observe(this){
            Glide.with(binding.imagePrueba).load(it).into(binding.imagePrueba)
        }

    }

    private fun onGalleryResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            binding.imagePrueba.setImageURI(uriImage)
            viewModel.upload(uriImage!!)

        }
    }


}