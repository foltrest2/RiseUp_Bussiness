package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.riseup.riseup_bussiness.databinding.ActivityDiscoPreviewBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.model.EventModel
import com.riseup.riseup_bussiness.util.BannerPartyAdapter
import com.riseup.riseup_bussiness.viewmodel.DiscoPreviewViewModel

class DiscoPreviewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDiscoPreviewBinding
    private val viewModel : DiscoPreviewViewModel by viewModels()

    private val adapter = BannerPartyAdapter{thisEvent -> onClickListener(thisEvent)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiscoPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val disco = loadDisco()!!
        Log.e(">>>", "$disco")

        setDiscoPropieties(disco)

        val bannersRecycler = binding.bannersRecyclerView
        bannersRecycler.setHasFixedSize(true)
        bannersRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        bannersRecycler.adapter = adapter

        //adapter.reset()
        for (event in disco.eventsID){
            Log.e(">>>","Evento agregado: ${event}")
            adapter.addEventCard(event)
        }

        binding.backPreviewDiscoBtn.setOnClickListener{
            finish()
            startActivity(Intent(this@DiscoPreviewActivity, ConfigUpdateAppearanceActivity::class.java))
        }


    }

    private fun onClickListener(thisEvent: EventModel) {
        Toast.makeText(this,thisEvent.toString(), Toast.LENGTH_SHORT).show()
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

    private fun setDiscoPropieties(disco : DiscoModel){
        binding.titleDiscoSelected.text = disco.name
        binding.diamondsInfoDiscoSelected.text = "¡Tienes 100 diamantes!"
        Glide.with(this).load(disco.bannerURL).into(binding.backgroundDiscoHome)
    }

}