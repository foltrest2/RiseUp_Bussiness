package com.riseup.riseup_bussiness.model

class CarritoModel {
    var carrito : MutableList<CarritoItem> = arrayListOf()

    constructor(carrito: MutableList<CarritoItem>) {
        this.carrito = carrito
    }

    override fun toString(): String {
        return "CarritoModel(carrito=$carrito)"
    }

}