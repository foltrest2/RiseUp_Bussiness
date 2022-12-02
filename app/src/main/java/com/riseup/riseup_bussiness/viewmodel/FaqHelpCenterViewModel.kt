package com.riseup.riseup_bussiness.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.riseup.riseup_bussiness.model.FAQInfoBlockModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaqHelpCenterViewModel : ViewModel(){

    private val faqArray = arrayListOf<FAQInfoBlockModel>()
    private val _faqs:MutableLiveData<ArrayList<FAQInfoBlockModel>> = MutableLiveData(arrayListOf())
    val faqs:LiveData<ArrayList<FAQInfoBlockModel>> get() = _faqs

    fun loadFaq(){
        viewModelScope.launch(Dispatchers.IO){
            Firebase.firestore.collection("Faq").get().addOnSuccessListener {data->
                for (doc in data.documents){
                    val thisFaq = doc.toObject(FAQInfoBlockModel::class.java)
                    faqArray.add(thisFaq!!)
                    _faqs.value = faqArray
                }
            }
        }
    }


}