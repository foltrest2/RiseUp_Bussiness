package com.riseup.riseup_bussiness.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riseup.riseup_bussiness.model.DiscoModel


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

