package com.riseup.riseup_bussiness.model

import java.util.*

data class OrdersModel (

    var id:String = "",
    var code:String = "",
    var diamonds:Int = 0,
    var state:Int = 0,
    var date: Date? = null,
    var shoppingCar:ArrayList<ShoppingCar>? = null,
    var discoID:String = "",
    var userID:String = "",
    var method:String = ""



) {
    override fun toString(): String {
        return "OrdersModel(id='$id', code='$code', diamonds=$diamonds, state=$state, date=$date, shoppingCar=$shoppingCar, discoID='$discoID', userID='$userID', method='$method')"
    }
}






