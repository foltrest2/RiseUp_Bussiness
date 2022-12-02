package com.riseup.riseup_bussiness.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class InitialConfigurationViewModel: ViewModel() {
    private val _inComingUser = MutableLiveData<DiscoModel>()
    val inComingUser: LiveData<DiscoModel> get() = _inComingUser

    fun setSpUser(user: DiscoModel) {
        _inComingUser.value = user
        Log.e(">>>", "IncomingUser Seted: ${_inComingUser.value}")
    }
        fun updateDiscoName(discoName: String, user: DiscoModel){
            user.name = discoName
            _inComingUser.postValue(user)
        }



}

