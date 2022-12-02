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
import com.google.firebase.storage.ktx.storage
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {

    private val _authState = MutableLiveData(
        AuthState(AuthResult.IDLE, "Starting...")
    )
    val authState: LiveData<AuthState> get() = _authState
    private lateinit var userReturn: DiscoModel

    private val _inComingProducts = MutableLiveData<List<ProductModel>>()
    val inComingProduct: MutableLiveData<List<ProductModel>> get() = _inComingProducts

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
                                Firebase.firestore.collection("Discos").document(fbuser.uid)
                                    .get()
                                    .addOnSuccessListener {
                                        Log.e(">>>", "Se esta guardando el usuario")
                                        userReturn = it.toObject(DiscoModel::class.java)!!
                                        if(userReturn.name == "" || userReturn.bannerCardID ==""
                                            || userReturn.bannerRef == "" || userReturn.bannerID == "" || userReturn.productsRef == ""){
                                            _authState.value = AuthState(AuthResult.SUCCESS, "VerifiedFirstTime")
                                        }
                                        else {
                                            Log.e(">>>","no es la primera vez que se logea el usuario")
                                            viewModelScope.launch(Dispatchers.IO) {
                                                val bannerURL = Firebase.storage.getReference(userReturn.bannerRef).child(userReturn.bannerID).downloadUrl.await()
                                                val bannerCardURL = Firebase.storage.getReference(userReturn.bannerRef).child(userReturn.bannerCardID).downloadUrl.await()

                                                withContext(Dispatchers.Main){
                                                    userReturn.bannerURL = bannerURL.toString()
                                                    userReturn.bannerCardURL = bannerCardURL.toString()
                                                }

                                                for(event in userReturn.eventsID){
                                                    val posterURL = Firebase.storage.getReference(userReturn.eventsRef).child(event.posterID).downloadUrl.await()
                                                    withContext(Dispatchers.Main){
                                                        event.posterURL = posterURL.toString()
                                                    }
                                                }
                                                withContext(Dispatchers.Main){
                                                    _authState.value = AuthState(AuthResult.SUCCESS, "Verified") }
                                            }

                                        }
                                    }.addOnFailureListener {
                                        Log.e(">>>", "No Se esta guardando el usuario")

                                        _authState.value =
                                            AuthState(AuthResult.FAIL, "networkError")
                                        return@addOnFailureListener
                                    }.await()
                                withContext(Dispatchers.Main){
                                    Log.e(">>>",""+task.result.additionalUserInfo?.isNewUser)
                                    //_authState.value = AuthState(AuthResult.SUCCESS, "Verified") }
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
    fun loadProducts(disco: DiscoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val products = Firebase.firestore.collection("Discos").document(disco.id)
                .collection("Products").get().await()
            val thisProducts = products.toObjects(ProductModel::class.java)
            _inComingProducts.postValue(thisProducts)
            for (product in thisProducts) {
                Log.e(">>>", "Acá el product ref ${disco.productsRef}")
                Log.e(">>>", "Acá el product image ${product.image}")
                val productImgURL = Firebase.storage.getReference(disco.productsRef)
                    .child(product.image).downloadUrl.await()
                product.imageURL = productImgURL.toString()
                Log.e(">>>", "Acá el product $product")
                Log.e(">>>", "Acá el product Image filodaputasetentagonorrea $productImgURL")
            }
        }
    }

    fun saveUserFromViewModel(): DiscoModel {

        return userReturn


    }


}

