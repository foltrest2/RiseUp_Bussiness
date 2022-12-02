package com.riseup.riseup_bussiness.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_bussiness.model.OrdersModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class OrdersListViewModel:ViewModel() {

    private var discoID = "O8xwe3LY4e4uYh9AGAHg"


    private val ordersArray = arrayListOf<OrdersModel>()
    private val _orders: MutableLiveData<ArrayList<OrdersModel>> = MutableLiveData(arrayListOf())
    val orders: LiveData<ArrayList<OrdersModel>> get() = _orders

    fun onOrderStateChange(order: OrdersModel, newState : Int){
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.firestore.collection("Sales").document(order.id).update("state",newState).addOnSuccessListener {
            }
        }
    }

    fun subscribeRealTimeOrders() {
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore
                .collection("Sales").whereEqualTo("discoID",discoID).addSnapshotListener { data, e ->
                    for (doc in data!!.documentChanges){
                        if(doc.type.name == "ADDED"){
                            val thisOrder = doc.document.toObject(OrdersModel::class.java)
                            ordersArray.add(thisOrder)
                            _orders.value = ordersArray
                        } else if (doc.type.name == "MODIFIED"){
                            val thisOrder = doc.document.toObject(OrdersModel::class.java)
                            for (order in ordersArray){
                                if(order.id.equals(thisOrder.id)){
                                    val index = ordersArray.indexOf(order)
                                    ordersArray[index] = thisOrder
                                    _orders.value = ordersArray
                                    break
                                }
                            }
                        }
                    }
                }
        }
    }


}