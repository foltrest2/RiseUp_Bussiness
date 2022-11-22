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


class InitialConfigurationViewModel: ViewModel() {
    private val _inComingUser = MutableLiveData<Disco>()
    val inComingUser: LiveData<Disco> get() = _inComingUser
    fun setSpUser(user: Disco) {
        _inComingUser.value = user
        Log.e(">>>", "IncomingUser Seted: ${_inComingUser.value}")
    }

        fun updateDiscoName(discoName: String, user: Disco){
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "Llego al updateDiscoName: ${_inComingUser.value}")
            Firebase.firestore.collection("Discos2").document(user.id).update("name", discoName).addOnSuccessListener{
                    user.name = discoName
                    user.bannerRef = "${discoName}/Banner/"
                    user.eventsRef = "${discoName}/Events/"
                    user.productsRef = "${discoName}/Products/"
                    _inComingUser.postValue(user)


            }.addOnFailureListener{

            }.await()

            }
        }
    fun updateDiscoRef(user:Disco){

        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "Llego al updateDiscoRef: ${_inComingUser.value}")
            Firebase.firestore.collection("Discos2").document(user.id).update("bannerRef","${user.name}/Banner/").addOnSuccessListener {
                Firebase.firestore.collection("Discos2").document(user.id).update("eventsRef","${user.name}/Events/").addOnSuccessListener {
                    Firebase.firestore.collection("Discos2").document(user.id).update("productsRef","${user.name}/Products/").addOnSuccessListener{

                    }
                }
            }.await()
        }

    }
}

