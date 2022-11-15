package com.riseup.riseup_bussiness.viewmodel

import com.riseup.riseup_bussiness.model.Disco



import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import kotlinx.coroutines.withContext


class LoginViewModel: ViewModel(){

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )
    val authState : LiveData<AuthState> get() = _authState
    private lateinit var userReturn : Disco

    //Accion de registro
    fun signIn(correo:String, pass:String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val result = Firebase.auth.signInWithEmailAndPassword(correo,pass).addOnCompleteListener { task->
                    if (!task.isSuccessful) {
                        try {
                            throw task.exception!!
                        }
                        catch (authException: FirebaseAuthException ) {

                            when(authException.errorCode){

                                "ERROR_WRONG_PASSWORD" -> _authState.value = AuthState(AuthResult.FAIL, "wrongPassword")
                                "ERROR_INVALID_EMAIL" -> _authState.value = AuthState(AuthResult.FAIL, "invalidEmail")
                                "ERROR_USER_NOT_FOUND" -> _authState.value = AuthState(AuthResult.FAIL, "userNotFound")

                            }
                        }
                    }else{
                        val fbuser = Firebase.auth.currentUser
                        if(fbuser!!.isEmailVerified){
                            //Pedimos el user en la db
                            viewModelScope.launch (Dispatchers.IO) {
                                Firebase.firestore.collection("Discos").document(fbuser.uid).get()
                                    .addOnSuccessListener {
                                        userReturn = it.toObject(Disco::class.java)!!
                                    }.addOnFailureListener {
                                        _authState.value = AuthState(AuthResult.FAIL, "networkError")
                                        return@addOnFailureListener
                                    }.await()
                                withContext(Dispatchers.Main){
                                    _authState.value = AuthState(AuthResult.SUCCESS, "SuccessAndVerified")
                                }
                            }
                        }else{
                            _authState.value = AuthState(AuthResult.SUCCESS, "NotVerified")
                        }
                    }
                }.await()

                withContext(Dispatchers.Main){ _authState.value = AuthState(AuthResult.SUCCESS, "Success") }

            }catch (ex: FirebaseAuthException){
                Log.e(">>>", ex.errorCode)
                withContext(Dispatchers.Main){ _authState.value = AuthState(AuthResult.FAIL, ex.localizedMessage)}
            }

        }
    }

    fun saveUserFromViewModel() : Disco {
        return userReturn
    }

}

