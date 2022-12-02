package com.riseup.riseup_bussiness.viewmodel

import android.util.Log
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

class EditProductListViewModel : ViewModel() {

    fun deleteProduct(disco: DiscoModel, product: ProductModel) {
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Discos").document(disco.id)
                .collection("Products").document(product.id).delete()
            Firebase.storage.reference.child("${disco.name}/Products/${product.image}").delete()
        }
    }

}