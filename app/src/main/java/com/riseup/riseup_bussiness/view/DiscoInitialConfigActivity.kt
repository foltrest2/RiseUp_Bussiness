package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_bussiness.databinding.ActivityDiscoInitialConfigBinding
import com.riseup.riseup_bussiness.viewmodel.InitialConfigurationViewModel

class DiscoInitialConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiscoInitialConfigBinding
    val viewmodel: InitialConfigurationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoInitialConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnToLoginICButton.setOnClickListener {
            finish()
            val intent = Intent(this@DiscoInitialConfigActivity, LoginActivity::class.java)
            startActivity(intent)
            val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
            //Debugguer, borrar despues
            val json = sp.getString("Usuario", "NO_USER")
            Toast.makeText(this,"A borrar tipo: $json", Toast.LENGTH_LONG).show()
            //Hasta aqui
            sp.edit().clear().apply()
            Firebase.auth.signOut()

        }
    }


}