package com.riseup.riseup_bussiness.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riseup.riseup_bussiness.model.Disco

class ConfigDiscoImagesViewModel:ViewModel() {

    private val _inComingUser = MutableLiveData<Disco>()
    val inComingUser: LiveData<Disco> get() = _inComingUser

    fun setSpUser(user: Disco) {
        _inComingUser.value = user
        Log.e(">>>", "IncomingUser Seted: ${_inComingUser.value}")
    }

    fun updateDiscoListImg(discoBannerImg: Uri?, user: Disco) {
        user.bannerCardID = discoBannerImg.toString()
        _inComingUser.postValue(user)
    }

    fun updateDiscoHomeImg(discoBannerHomeImg: Uri?, user: Disco) {

        user.bannerID = discoBannerHomeImg.toString()
        _inComingUser.postValue(user)
    }

}