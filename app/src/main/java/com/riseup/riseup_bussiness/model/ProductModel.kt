package com.riseup.riseup_bussiness.model

import java.io.Serializable

data class ProductModel (
    val id:String = "",
    var category:String = "",
    var price:Int = 0,
    var diamondPrice:Int? = null,
    var name:String = "",
    var quantity:Int = 0,
    var image:String = "",
    var imageURL:String? = null
) : Serializable


