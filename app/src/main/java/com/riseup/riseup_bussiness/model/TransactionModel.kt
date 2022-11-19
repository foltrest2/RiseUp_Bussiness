package com.riseup.riseup_bussiness.model


import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

data class TransactionModel(
    val id : String = "",
    var code : String = "",
    var date : Date? = null,
    var diamonds: Int = 0,
    val discoID : String = "",
    var method: String = "",
    var shoppingCar : ArrayList<ProductsShoppingCarModel>? = null,
    var state : Int = 0,
    val userID : String = "",
    var discoName : String? = null,
    var totalPay : Int? = null
) : Serializable {

}