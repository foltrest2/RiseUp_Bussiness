package com.riseup.riseup_bussiness.model

import java.io.Serializable

data class Disco(
    var id:String = "",
    var name:String = "",
    var email:String  = "",
    var bannerID:String = "",
    var bannerCardID:String = "",
    var bannerRef:String = "",
    var eventsID: ArrayList<String>? = null,
    var eventsRef:String = "",
    var productsRef:String = ""


) : Serializable{
    override fun toString(): String {
        return "Disco(name='$name', bannerID='$bannerID',bannerCardID='$bannerCardID',bannerRef='$bannerRef',productsRef='$productsRef',eventsRef='$eventsRef')"
    }
}
