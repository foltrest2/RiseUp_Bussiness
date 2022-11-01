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

    private var discoID = "1otzuoJuS4ZrQQH6REsL"


    private val ordersArray = arrayListOf<OrdersModel>()
    private val _orders: MutableLiveData<ArrayList<OrdersModel>> = MutableLiveData(arrayListOf())
    val orders: LiveData<ArrayList<OrdersModel>> get() = _orders


    /**
    fun suscribeToOrders(){
        viewModelScope.launch(Dispatchers.IO){
            lateinit var order:OrdersModel
            val result = Firebase.firestore.collection("Ventas")
                .whereEqualTo("idDiscoteca",discoID).get().await()
            for (doc in result.documents){
                //Log.e(">>>",doc.toString())
                val order = doc.toObject(OrdersModel::class.java)
                Log.e(">>>",order.toString())
                withContext(Dispatchers.Main){
                    ordersArray.add(order!!)
                    _orders.value = ordersArray
                }

                //withContext(Dispatchers.Main){suscribeRealTimeOrders(order!!)}
            }
        }
    }
    */

    fun onOrderStateChange(order: OrdersModel, newState : Int){
        viewModelScope.launch(Dispatchers.IO) {
            //val index = ordersArray.indexOf(order)
            Firebase.firestore.collection("Sales").document(order.id).update("state",newState).await()
        }

    }

    fun suscribeRealTimeOrders() {
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore
                .collection("Sales").whereEqualTo("discoID",discoID).addSnapshotListener { data, e ->
                    for (doc in data!!.documentChanges){
                        if(doc.type.name == "ADDED"){
                            val thisOrder = doc.document.toObject(OrdersModel::class.java)
                            Log.e(">>>", "ADEED: $thisOrder")
                            ordersArray.add(thisOrder)
                            _orders.value = ordersArray
                        } else if (doc.type.name == "MODIFIED"){
                            val thisOrder = doc.document.toObject(OrdersModel::class.java)
                            Log.e(">>>", "MODIFIED: $thisOrder")
                            for (order in ordersArray){
                                if(order.id.equals(thisOrder.id)){
                                    val index = ordersArray.indexOf(order)
                                    ordersArray[index] = thisOrder
                                    Log.e(">>>", "INDEX_TO_MOD: $index")
                                    //_orders.value = ordersArray
                                    break
                                }
                            }
                        }
                    }
                }
        }

    }


}