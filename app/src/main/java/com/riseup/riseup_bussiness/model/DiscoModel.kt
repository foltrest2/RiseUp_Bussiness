package com.riseup.riseup_bussiness.model

import java.io.Serializable

data class DiscoModel (
    val id:String = "",
    var name:String = "",
    var bannerID:String = "",
    var bannerRef:String = "",
    var bannerCardID:String = "",
    var email:String = "",
    var eventsID:ArrayList<EventModel> = arrayListOf(),
    var eventsRef:String = "",
    var products:ArrayList<ProductModel> = arrayListOf(),
    var bannerURL:String? = null,
    var productsRef:String? = null

) : Serializable {
    override fun toString(): String {
        return "DiscoModel(id='$id', name='$name', bannerID='$bannerID', bannerRef='$bannerRef', bannerCardID='$bannerCardID', email='$email', eventsID=$eventsID, eventsRef='$eventsRef', products=$products, bannerURL=$bannerURL)"
    }
}