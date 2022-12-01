package com.riseup.riseup_bussiness.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riseup.riseup_bussiness.model.Disco



class InitialConfigurationViewModel: ViewModel() {
    private val _inComingUser = MutableLiveData<Disco>()
    val inComingUser: LiveData<Disco> get() = _inComingUser
    fun setSpUser(user: Disco) {
        _inComingUser.value = user
        Log.e(">>>", "IncomingUser Seted: ${_inComingUser.value}")
    }
        fun updateDiscoName(discoName: String, user: Disco){
            user.name = discoName
            _inComingUser.postValue(user)
        }

}

