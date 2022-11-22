package com.riseup.riseup_bussiness.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.model.Disco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ConfigDiscoImagesViewModel:ViewModel() {

    private val _inComingUser = MutableLiveData<Disco>()
    val inComingUser: LiveData<Disco> get() = _inComingUser

    fun setSpUser(user: Disco) {
        _inComingUser.value = user
        Log.e(">>>", "IncomingUser Seted: ${_inComingUser.value}")
    }

    fun updateDiscoListImg(discoBannerImg: Uri?, user: Disco) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoListImg: ${user.name}")
            var filename = "DiscoListImage.png"
            Firebase.storage.reference.child("${user.name}/Banner/${filename}")
                .putFile(discoBannerImg!!).addOnSuccessListener {
                    Firebase.firestore.collection("Discos2").document(user.id)
                        .update("bannerCardID", filename).addOnSuccessListener {
                            user.bannerCardID = discoBannerImg.toString()
                            _inComingUser.postValue(user)
                        }
                }
        }
    }

    fun updateDiscoHomeImg(discoBannerHomeImg: Uri?, user: Disco) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoHomeImg: ${user.name}")
            var filename = "DiscoHomeImage.png"
            Firebase.storage.reference.child("${user.name}/Banner/${filename}")
                .putFile(discoBannerHomeImg!!).addOnSuccessListener {
                    Firebase.firestore.collection("Discos2").document(user.id)
                        .update("bannerID", filename).addOnSuccessListener {
                            user.bannerID = discoBannerHomeImg.toString()
                            _inComingUser.postValue(user)
                        }
                }
        }
    }
    /*


    fun updateDiscoListImg(discoBannerImg: Uri?, user: Disco) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoListImg: ${user.name}")

            Firebase.storage.reference.child("${user.name}/Banner/DiscoListImage.png")
                .putFile(discoBannerImg!!)
            withContext(Dispatchers.Main) {
                user.bannerCardID = discoBannerImg.toString()
                _inComingUser.postValue(user)
            }

        }
    }

    fun updateDiscoHomeImg(discoBannerHomeImg: Uri?, user: Disco) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoHomeImg: ${user.name}")

            Firebase.storage.reference.child("${user.name}/Banner/DiscoHomeImage.png")
                .putFile(discoBannerHomeImg!!)
            withContext(Dispatchers.Main) {
                user.bannerID = discoBannerHomeImg.toString()
                _inComingUser.postValue(user)
            }

        }

    }
/*
    suspend fun updateCardID(user: Disco): Boolean = suspendCoroutine{ cont ->
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "cardID que llega al updateCardID: ${user.bannerCardID}")
            Firebase.firestore.collection("Discos2").document(user.id)
                .update("bannerCardID", user.bannerCardID).addOnCompleteListener {
                    cont.resume(true)
                }.addOnFailureListener{
                    cont.resume(false)
                }.await()
        }


    }

    suspend fun updateBannerID(user: Disco): Boolean = suspendCoroutine{ cont ->
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "bannerID que llega al updateBannerID: ${user.bannerID}")
            Firebase.firestore.collection("Discos2").document(user.id)
                .update("bannerID", user.bannerID).addOnCompleteListener {
                    cont.resume(true)
                }.addOnFailureListener{
                    cont.resume(false)
                }.await()
        }


    }



 */

     */
}