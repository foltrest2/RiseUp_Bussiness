package com.riseup.riseup_bussiness.viewmodel

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.model.Disco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.util.*

class RegisterViewModel: ViewModel() {

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )
    val authState : LiveData<AuthState> get() = _authState

    //Accion de registro
    fun signUp(correo:String,pass:String){
        viewModelScope.launch (Dispatchers.IO) {
            try {
                val result = Firebase.auth.createUserWithEmailAndPassword(
                    correo,
                    pass
                ).addOnCompleteListener { task ->
                    if (!task.isSuccessful) {

                        try {
                            throw task.exception!!

                        } catch (weakPassword: FirebaseAuthWeakPasswordException) {
                            _authState.value = AuthState(AuthResult.FAIL, "WeakPass")
                        } catch (malformedEmail: FirebaseAuthInvalidCredentialsException) {
                            _authState.value = AuthState(AuthResult.FAIL, "invalidEmail")

                        } catch (existEmail: FirebaseAuthUserCollisionException) {

                            _authState.value = AuthState(AuthResult.FAIL, "RepeatedEmail")

                        } catch (e: Exception) {

                        }

                    } else {
                        Firebase.auth.currentUser!!.sendEmailVerification()
                        Log.e(">>>", Firebase.auth.currentUser!!.uid)
                        //Registrar el objeto en Firestore
                        val disco = Disco(
                            Firebase.auth.currentUser!!.uid,
                            "",
                            correo,
                            "",
                            "",
                            "",
                            arrayListOf(),
                            "",
                            ""
                        )
                        Firebase.firestore.collection("Discos2")
                            .document(disco.id).set(disco)
                        _authState.value = AuthState(AuthResult.SUCCESS, "Success")

                    }
                }.await()

            }catch(ex:Exception){

            }
        }
    }



    fun validatePassword(pass:String, confpass: String):Boolean{
        var eqpass = true
        if(pass != confpass){
            eqpass = false
        }else{

        }
        return eqpass
    }
}
data class AuthState(val result:AuthResult, val message:String)
enum class AuthResult{ IDLE, FAIL, SUCCESS }