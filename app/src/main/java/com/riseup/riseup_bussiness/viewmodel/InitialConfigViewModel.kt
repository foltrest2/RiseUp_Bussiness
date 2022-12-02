package com.riseup.riseup_bussiness.viewmodel

import android.util.Log
import androidx.core.net.toUri
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

class InitialConfigViewModel:ViewModel() {
    private val _inComingUser = MutableLiveData<DiscoModel>()
    val inComingUser : LiveData<DiscoModel> get() = _inComingUser
    private val _inComingProducts = MutableLiveData<ArrayList<ProductModel>>()
    val inComingProduct: MutableLiveData<ArrayList<ProductModel>> get() =_inComingProducts

    fun setSpUser(user: DiscoModel){
        _inComingUser.value = user
        Log.e(">>>","IncomingUser Seted: ${_inComingUser.value}")
    }

    fun setSpProducts(products:ArrayList<ProductModel>){
        _inComingProducts.value = products
        Log.e(">>>","IncomingProducts Seted: ${_inComingProducts.value}")
    }

    fun updateDiscoName(user: DiscoModel){
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "Llego al updateDiscoName: ${_inComingUser.value}")
            Firebase.firestore.collection("Discos").document(user.id).update("name", user.name).addOnSuccessListener{
                user.bannerRef = "${user.name}/Banner/"
                user.eventsRef = "${user.name}/Events/"
                user.productsRef = "${user.name}/Products/"
                _inComingUser.postValue(user)


            }.addOnFailureListener{

            }.await()

        }

    }
    fun updateDiscoRef(user:DiscoModel){

        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "Llego al updateDiscoRef: ${_inComingUser.value}")
            Firebase.firestore.collection("Discos").document(user.id).update("bannerRef","${user.name}/Banner/").addOnSuccessListener {
                Firebase.firestore.collection("Discos").document(user.id).update("eventsRef","${user.name}/Events/").addOnSuccessListener {
                    Firebase.firestore.collection("Discos").document(user.id).update("productsRef","${user.name}/Products/").addOnSuccessListener{

                    }
                }
            }.await()
        }

    }
    fun updateDiscoListImg(user: DiscoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoListImg: ${user.name}")
            var filename = "DiscoListImage.png"
            Firebase.storage.reference.child("${user.name}/Banner/${filename}")
                .putFile(user.bannerCardID!!.toUri()!!).addOnSuccessListener {
                    Firebase.firestore.collection("Discos").document(user.id)
                        .update("bannerCardID", filename).addOnSuccessListener {
                            user.bannerCardID = filename
                            _inComingUser.postValue(user)

                        }
                }
        }
    }

    fun updateDiscoEventsStorage(user:DiscoModel){
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoEventsStorage: ${user.name}")
            Firebase.storage.reference.child("${user.name}/Events/")
        }
    }

    fun updateDiscoHomeImg( user: DiscoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.e(">>>", "user que llega al updateDiscoHomeImg: ${user.name}")
            var filename = "DiscoHomeImage.png"
            Firebase.storage.reference.child("${user.name}/Banner/${filename}")
                .putFile(user.bannerID!!.toUri()!!).addOnSuccessListener {
                    Firebase.firestore.collection("Discos").document(user.id)
                        .update("bannerID", filename).addOnSuccessListener {
                            user.bannerID = filename
                            _inComingUser.postValue(user)
                        }
                }
        }
    }

    fun updateDiscoProducts(discoProducts:ArrayList<ProductModel>,user: DiscoModel){
        viewModelScope.launch(Dispatchers.IO) {
            var db = Firebase.firestore
            var dbs = Firebase.storage
            var batch = db.batch()
            for(product in discoProducts) {
                var prodName = "${product.name}.png".replace(" ", "")
                var docRef = db.collection("Discos").document(user.id).collection("Products").document(product.id)
                 dbs.reference.child("${user.name}/Products/${prodName}").putFile(product.image.toUri())
                product.image = prodName
                _inComingProducts.postValue(discoProducts)
                batch.set(docRef, product)
            }
            batch.commit()

        }


    }



}