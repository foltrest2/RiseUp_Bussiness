package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.riseup.riseup_bussiness.databinding.ActivityUpdateAppearanceBinding

class ConfigUpdateAppearanceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateAppearanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAppearanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ::onGalleryResult)

        binding.backArrowAppeareanceConfigBtn.setOnClickListener {
            startActivity(Intent(this@ConfigUpdateAppearanceActivity, ConfigurationActivity::class.java))
        }

        binding.bannerSetBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            galleryLauncher.launch(intent)

        }



    }

    fun onGalleryResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            uriImage?.let {
                binding.imagePrueba.setImageURI(uriImage)
            }
        }
    }
}