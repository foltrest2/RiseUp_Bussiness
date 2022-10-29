package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.*
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.databinding.ActivityUpdateAppearanceBinding
import com.riseup.riseup_bussiness.model.Disco
import kotlinx.coroutines.Dispatchers
import java.util.*

class ConfigUpdateAppearanceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateAppearanceBinding

    private lateinit var galleryLauncher : ActivityResultLauncher<Intent>

    private lateinit var discoID : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateAppearanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        discoID = "1otzuoJuS4ZrQQH6REsL"

        galleryLauncher = registerForActivityResult(StartActivityForResult(), ::onGalleryResult)

        updateImage()

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
            binding.imagePrueba.setImageURI(uriImage)

            //Upload
            val filename = UUID.randomUUID().toString()
            Firebase.storage.getReference("/Espacio 10-60/Banner").child(filename).putFile(uriImage!!)
            Firebase.firestore.collection("Discotecas").document(discoID).update("bannerID", filename)
            Log.e(">>>", "UUID: ${filename}")

        }
    }

    fun updateImage(){
        Firebase.firestore.collection("Discotecas").document(discoID).get().addOnSuccessListener {
            val updatedDisco = it.toObject(Disco::class.java)
            val bannerID = updatedDisco?.bannerID
            Log.e(">>>", "Updated Disco: "+updatedDisco+"thisBannerID: "+bannerID)
            downloadBanner(bannerID)
        }
    }

    fun downloadBanner(bannerID: String?) {
        if(bannerID!!.isEmpty()) return
        Firebase.storage.getReference("Espacio 10-60/Banner").child(bannerID).downloadUrl.addOnSuccessListener {
            Glide.with(binding.imagePrueba).load(it).into(binding.imagePrueba)
        }
    }
}