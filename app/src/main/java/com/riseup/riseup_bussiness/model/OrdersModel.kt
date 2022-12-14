package com.riseup.riseup_bussiness.model

import java.util.*
import kotlin.collections.ArrayList


data class OrdersModel (

    val id:String = "",
    var code:String = "",
    var diamonds:Int = 0,
    var state:Int = 0,
    var date: Date? = null,
    var shoppingCar:ArrayList<ShoppingCar>? = null,
    val discoID:String = "",
    val userID:String = "",
    var method:String = "",
    var visibility:Boolean = false



) {
    override fun toString(): String {
        return "OrdersModel(id='$id', code='$code', diamonds=$diamonds, state=$state, date=$date, shoppingCar=$shoppingCar, discoID='$discoID', userID='$userID', method='$method', visibility=$visibility)"
    }
}






