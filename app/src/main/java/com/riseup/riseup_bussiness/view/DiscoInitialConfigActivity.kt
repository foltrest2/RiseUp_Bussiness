package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.riseup.riseup_bussiness.databinding.ActivityDiscoInitialConfigBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.viewmodel.InitialConfigurationViewModel


class DiscoInitialConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiscoInitialConfigBinding
    private lateinit var user : DiscoModel
    val viewmodel: InitialConfigurationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoInitialConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Inicializacion de usuario
        user = loadUser()!!
        //Inicializacion del viewmodel
        viewmodel.setSpUser(user)

        binding.returnToLoginICButton.setOnClickListener {
            finish()
            val intent = Intent(this@DiscoInitialConfigActivity, LoginActivity::class.java)
            startActivity(intent)
            val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
            //Debugguer, borrar despues
            val json = sp.getString("Usuario", "NO_USER")
            Toast.makeText(this, "A borrar tipo: $json", Toast.LENGTH_LONG).show()
            //Hasta aqui
            sp.edit().clear().apply()
            Firebase.auth.signOut()

        }
        viewmodel.inComingUser.observe(this) {
            Log.e(">>>", "Actualizado en observer DiscoInitialConfig: ${it}")
            saveUserSp(it)
        }

        binding.continueICBtn.setOnClickListener {
            if (binding.DiscoNameICET.text.isNotEmpty()) {

                 viewmodel.updateDiscoName(binding.DiscoNameICET.text.toString(), user)
                     //viewmodel.updateDiscoRef(user)
            }
            finish()
            startActivity(Intent(this, ConfigDiscoImagesActivity::class.java))


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

        Log.e(">>>", "Guardando en DiscoInitialConfig: ${user}")
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario", json).apply()
    }


}


