package com.riseup.riseup_bussiness.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.riseup.riseup_bussiness.databinding.ActivityConfigDiscoImagesBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.util.ErrorDialog
import com.riseup.riseup_bussiness.viewmodel.ConfigDiscoImagesViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConfigDiscoImagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfigDiscoImagesBinding
    private lateinit var user : DiscoModel
    private lateinit var bannerLImg: Uri
    private lateinit var bannerHomeImg: Uri
    val viewmodel:ConfigDiscoImagesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigDiscoImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializacion de la galeria
        val galleryHomeLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onGalleryHomeResult)
        val galleryListLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onGalleryListResult)
        //Pedir permisos
        requestPermissions(arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ),1)
        //Inicializacion de usuario
        user = loadUser()!!
        //Inicializacion del viewmodel
        viewmodel.setSpUser(user)
        viewmodel.inComingUser.observe(this){
            Log.e(">>>", "Actualizado en observer DiscoImage: ${it}")
            saveUserSp(it)
        }
        binding.chooseDiscoListImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type= "image/*"
            galleryListLauncher.launch(intent)
        }
        binding.chooseDiscoHomeImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type= "image/*"
            galleryHomeLauncher.launch(intent)

        }
        binding.continueICDIBtn.setOnClickListener {

               if (bannerLImg != null && bannerHomeImg != null) {
                   viewmodel.updateDiscoListImg(bannerLImg, user)
                   viewmodel.updateDiscoHomeImg(bannerHomeImg,user)
                   finish()
                   startActivity(Intent(this, AddProductActivity::class.java))
               }else{
                   val dialogFragmentP = ErrorDialog()
                   val bundle = Bundle()
                   bundle.putString("TEXT","EmptyImages")
                   dialogFragmentP.arguments = bundle
                   dialogFragmentP.show(supportFragmentManager,"EmptyImagesDialog")

               }

        }
        binding.returnToLoginICButton.setOnClickListener {

            finish()
            startActivity(Intent(this, LoginActivity::class.java))
            val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
            val json = sp.getString("Usuario", "NO_USER")
            Toast.makeText(this,"A borrar tipo: $json", Toast.LENGTH_LONG).show()
            sp.edit().clear().apply()
            Firebase.auth.signOut()
        }

    }


    private fun loadUser(): DiscoModel? {
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
    private fun onGalleryHomeResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
                   bannerHomeImg = uriImage!!

           }
        }

    private fun onGalleryListResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            bannerLImg = uriImage!!

        }
    }

}