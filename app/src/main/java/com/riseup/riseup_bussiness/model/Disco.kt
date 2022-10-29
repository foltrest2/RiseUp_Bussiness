package com.riseup.riseup_bussiness.model

import java.io.Serializable

data class Disco(
    var id:String = "",
    var nombre:String = "",
    var email:String  = "",
    var bannerID:String = "",
    var bannerRef:String = "",
    var eventosID: ArrayList<String>? = null,
    var eventosRef:String = ""


) : Serializable{
    override fun toString(): String {
        return "Disco(nombre='$nombre', bannerID='$bannerID')"
    }
}
