package com.riseup.riseup_bussiness.viewmodel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.model.DiscoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class ConfigUpdateAppearanceViewModel : ViewModel() {

    lateinit var discoID : String
    private val _incomingBanner = MutableLiveData<String>()
    val incomingBanner : LiveData<String> get() = _incomingBanner


    fun updateImage(){
        Firebase.firestore.collection("Discos").document(discoID).get().addOnSuccessListener {
            val updatedDisco = it.toObject(DiscoModel::class.java)
            val bannerID = updatedDisco?.bannerID
            downloadBanner(bannerID)
        }
    }

   private fun downloadBanner(bannerID: String?) {
        if(bannerID!!.isEmpty()){
            return
        } else {
            viewModelScope.launch(Dispatchers.IO){
                val banner = Firebase.storage.getReference("Espacio 10-60/Banner").child(bannerID).downloadUrl.await()
                withContext(Dispatchers.Main){
                    _incomingBanner.value = banner.toString()
                }
            }
        }

    }

    fun upload(uriImage : Uri) {
        //Upload
        val filename = UUID.randomUUID().toString()
        viewModelScope.launch(Dispatchers.IO){

            Firebase.storage.getReference("/Espacio 10-60/Banner").child(filename).putFile(uriImage)
            Firebase.firestore.collection("Discos").document(discoID).update("bannerID", filename)
        }
    }

}