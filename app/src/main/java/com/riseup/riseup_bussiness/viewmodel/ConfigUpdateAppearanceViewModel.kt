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

    lateinit var disco : DiscoModel
    private val _incomingBanner = MutableLiveData<String>()
    val incomingBanner : LiveData<String> get() = _incomingBanner
    private val _incomingBannerCard = MutableLiveData<String>()
    val incomingBannerCard : LiveData<String> get() = _incomingBannerCard


    fun loadBannersURL(){
        downloadCardBanner(disco)
        downloadMainBanner(disco)
    }

   private fun downloadMainBanner(disco : DiscoModel) {
        if(disco.bannerID!!.isEmpty()){
            return
        } else {
            viewModelScope.launch(Dispatchers.IO){
                val banner = Firebase.storage.getReference(disco.bannerRef).child(disco.bannerID).downloadUrl.await()
                withContext(Dispatchers.Main){
                    _incomingBanner.value = banner.toString()
                }
            }
        }
    }

    private fun downloadCardBanner(disco : DiscoModel) {
        if(disco.bannerCardID.isEmpty()){
            return
        } else {
            viewModelScope.launch(Dispatchers.IO){
                val banner = Firebase.storage.getReference(disco.bannerRef).child(disco.bannerCardID).downloadUrl.await()
                withContext(Dispatchers.Main){
                    _incomingBannerCard.value = banner.toString()
                }
            }
        }
    }

    fun uploadMainBanner(uriImage : Uri) {
        //Upload
        val filename = UUID.randomUUID().toString()
        viewModelScope.launch(Dispatchers.IO){
            Firebase.storage.getReference(disco.bannerRef).child(filename).putFile(uriImage).await()
            Firebase.firestore.collection("Discos").document(disco.id).update("bannerID", filename).await()
            val newBannerURL = Firebase.storage.getReference(disco.bannerRef).child(filename).downloadUrl.await()
            withContext(Dispatchers.Main){
                _incomingBanner.value = newBannerURL.toString()
            }
        }
    }

    fun uploadcardBanner(uriImage : Uri) {
        //Upload
        val filename = UUID.randomUUID().toString()
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.storage.getReference(disco.bannerRef).child(filename).putFile(uriImage).await()
            Firebase.firestore.collection("Discos").document(disco.id).update("bannerCardID", filename).await()
            val newBannerCardURL = Firebase.storage.getReference(disco.bannerRef).child(filename).downloadUrl.await()
            withContext(Dispatchers.Main){
                _incomingBannerCard.value = newBannerCardURL.toString()
            }
        }
    }

}