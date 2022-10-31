package com.riseup.riseup_bussiness.model

import java.util.*

class OrdersBlockModel {

    var id:String
    var codigo:String
    var diamantes:Int
    var estado:Int
    var fecha: Date
    var carrito:CarritoModel
    var idDiscoteca:String
    var idUsuario:String
    var metodo:String

    constructor(
        id: String,
        codigo: String,
        diamantes: Int,
        estado: Int,
        fecha: Date,
        carrito:CarritoModel,
        idDiscoteca: String,
        idUsuario: String,
        metodo: String
    ) {
        this.id = id
        this.codigo = codigo
        this.diamantes = diamantes
        this.estado = estado
        this.fecha = fecha
        this.carrito = carrito
        this.idDiscoteca = idDiscoteca
        this.idUsuario = idUsuario
        this.metodo = metodo
    }

    override fun toString(): String {
        return "OrdersBlockModel(id='$id', codigo='$codigo', diamantes=$diamantes, estado=$estado, fecha=$fecha, carrito=$carrito, idDiscoteca='$idDiscoteca', idUsuario='$idUsuario', metodo='$metodo')"
    }


}

