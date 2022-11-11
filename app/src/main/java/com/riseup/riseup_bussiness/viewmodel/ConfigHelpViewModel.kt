package com.riseup.riseup_bussiness.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class ConfigHelpViewModel : ViewModel() {

    fun sendReport(report : String){
        val id = UUID.randomUUID().toString()
        viewModelScope.launch(Dispatchers.IO){
            val newReport = Bug(id,report)
            Firebase.firestore.collection("Bugs").document(newReport.id).set(newReport).addOnSuccessListener {

            }.await()
        }
    }
}

data class Bug(var id:String, var info:String)