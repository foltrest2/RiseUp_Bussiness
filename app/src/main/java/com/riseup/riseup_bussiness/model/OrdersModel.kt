package com.riseup.riseup_bussiness.model

import java.util.*

data class OrdersModel (

    var id:String = "",
    var codigo:String = "",
    var diamantes:Int = 0,
    var estado:Int = 0,
    var fecha: Date? = null,
    var carrito:ArrayList<CarritoItem>? = null,
    var idDiscoteca:String = "",
    var idUsuario:String = "",
    var metodo:String = ""



) {
    override fun toString(): String {
        return "OrdersModel(id='$id', codigo='$codigo', diamantes=$diamantes, estado=$estado, fecha=$fecha, carrito=$carrito, idDiscoteca='$idDiscoteca', idUsuario='$idUsuario', metodo='$metodo')"
    }
}






