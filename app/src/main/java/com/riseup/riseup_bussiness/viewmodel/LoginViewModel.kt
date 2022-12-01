package com.riseup.riseup_bussiness.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_bussiness.model.Disco
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
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

                                "ERROR_WRONG_PASSWORD" ->
                                    _authState.value = AuthState(AuthResult.FAIL, "wrongPassword")
                                "ERROR_INVALID_EMAIL" ->
                                    _authState.value = AuthState(AuthResult.FAIL, "invalidEmail")
                                "ERROR_USER_NOT_FOUND" ->
                                    _authState.value = AuthState(AuthResult.FAIL, "userNotFound")

                            }
                        }
                    }else{

                        val fbuser = Firebase.auth.currentUser

                        if(fbuser!!.isEmailVerified){
                            Log.e(">>>","el usuario esta verificado")
                            //Pedimos el user en la db
                                viewModelScope.launch ( Dispatchers.IO) {
                                    Firebase.firestore.collection("Discos2").document(fbuser.uid)
                                        .get()
                                        .addOnSuccessListener {
                                            Log.e(">>>", "Se esta guardando el usuario")
                                            userReturn = it.toObject(Disco::class.java)!!
                                            if(userReturn.name == "" || userReturn.bannerCardID == "" || userReturn.bannerCardID ==""
                                                || userReturn.bannerRef == "" || userReturn.bannerID == "" || userReturn.productsRef == ""){
                                                _authState.value = AuthState(AuthResult.SUCCESS, "VerifiedFirstTime")
                                            }
                                        }.addOnFailureListener {
                                            Log.e(">>>", "No Se esta guardando el usuario")

                                            _authState.value =
                                                AuthState(AuthResult.FAIL, "networkError")
                                            return@addOnFailureListener
                                        }.await()
                                    withContext(Dispatchers.Main){
                                        Log.e(">>>",""+task.result.additionalUserInfo?.isNewUser)
                                        if (
                                            fbuser.metadata!!.creationTimestamp == fbuser.metadata!!.lastSignInTimestamp
                                        ) {
                                            Log.e(">>>","primera vez que se logea esl usuario")
                                            _authState.value = AuthState(AuthResult.SUCCESS, "VerifiedFirstTime")
                                        } /*
                                        else {
                                            Log.e(">>>","no es la primera vez que se logea el usuario")
                                            _authState.value = AuthState(AuthResult.SUCCESS, "Verified") }
                                        */
                                        }

                                }
                        }else{
                            _authState.value = AuthState(AuthResult.SUCCESS, "NotVerified")
                        }
                    }
                }.await()

            }catch (ex:Exception){
                //Log.e(">>>", ex.errorCode)
                // _authState.value = AuthState(AuthResult.FAIL, ex.localizedMessage)
            }
        }
    }

    fun saveUserFromViewModel() : Disco {

        return userReturn


    }


}
