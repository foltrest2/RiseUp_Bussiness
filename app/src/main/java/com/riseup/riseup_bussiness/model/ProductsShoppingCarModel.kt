package com.riseup.riseup_bussiness.model

import java.io.Serializable

data class ProductsShoppingCarModel(
    var image:String = "",
    var name:String = "",
    var price: Int = 0,
    var lot:Int = 0
    ) : Serializable {

}
