package com.riseup.riseup_bussiness.model

class CarritoItem {

    var cantidad:Int = 0
    var idProducto:String = ""

    constructor(cantidad: Int, idProducto: String) {
        this.cantidad = cantidad
        this.idProducto = idProducto
    }

    override fun toString(): String {
        return "CarritoItem(cantidad=$cantidad, idProducto='$idProducto')"
    }

}