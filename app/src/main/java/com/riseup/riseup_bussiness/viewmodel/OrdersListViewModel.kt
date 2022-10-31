package com.riseup.riseup_bussiness.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_bussiness.model.OrdersBlockModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class OrdersListViewModel:ViewModel() {

    private var discoID = "1otzuoJuS4ZrQQH6REsL"


    private val ordersArray = arrayListOf<OrdersBlockModel>()
    private val _orders: MutableLiveData<ArrayList<OrdersBlockModel>> = MutableLiveData(arrayListOf())
    val orders: LiveData<ArrayList<OrdersBlockModel>> get() = _orders

    /**
    fun suscribeToOrders(){
        viewModelScope.launch(Dispatchers.IO){
            lateinit var order:OrdersBlockModel
            val result = Firebase.firestore.collection("Ventas")
                .whereEqualTo("idDiscoteca",discoID).get().await()
            for (doc in result.documents){
                val order = doc.toObject(OrdersBlockModel::class.java)
                withContext(Dispatchers.Main){suscribeRealTimeOrders(order!!)}
            }
        }
    }
*/

    private fun suscribeRealTimeOrders(order: OrdersBlockModel) {
        Firebase.firestore
            .collection("Ventas").document(order.id)
            .collection("Ventas").addSnapshotListener { data, e ->
                for (doc in data!!.documentChanges){
                    if(doc.type.name == "ADDED"){
                        val thisOrder = doc.document.toObject(OrdersBlockModel::class.java)
                        Log.e(">>>", "ADEED: "+thisOrder.toString())
                        ordersArray.add(thisOrder)
                        _orders.value = ordersArray
                    } else if (doc.type.name == "MODIFIED"){
                        val thisOrder = doc.document.toObject(OrdersBlockModel::class.java)
                        Log.e(">>>", "MODIFIED: "+thisOrder.toString())
                        //ordersArray.
                        //_orders.value = ordersArray
                    }
                }
            }
    }

}