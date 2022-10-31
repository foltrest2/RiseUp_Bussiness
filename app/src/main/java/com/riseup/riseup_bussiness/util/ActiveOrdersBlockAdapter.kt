package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.CarritoItem
import com.riseup.riseup_bussiness.model.CarritoModel
import com.riseup.riseup_bussiness.model.OrdersBlockModel
import com.riseup.riseup_bussiness.view.ActiveOrdersInfoBlocksView
import java.util.*
import kotlin.collections.ArrayList

class ActiveOrdersBlockAdapter(private val onClickListener:(OrdersBlockModel) -> Unit) : RecyclerView.Adapter<ActiveOrdersInfoBlocksView>(){

     private val ordersBlocks = ArrayList<OrdersBlockModel>()


    init {
        val date = Date(2022, 10, 30)
        val carritoItem = CarritoItem(2,"j0WVgXvjZT1h0A7C91Vn")
        val carritoItem2 = CarritoItem(1,"MWGcydbi3aAjfmKpAYr7")
        val productsList : MutableList<CarritoItem> = arrayListOf()
        productsList.add(carritoItem)
        productsList.add(carritoItem2)
        val carritoItems = CarritoModel(productsList)

        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"C5S0V",20,0,date,carritoItems,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"C5S1V",20,0,date,carritoItems,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"C5S2V",20,0,date,carritoItems,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrdersInfoBlocksView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.active_orders_block_row, parent, false)
        val blocksOrdersView = ActiveOrdersInfoBlocksView(row)
        return blocksOrdersView
    }

    override fun onBindViewHolder(skeleton: ActiveOrdersInfoBlocksView, position: Int) {
        val ordersList = ordersBlocks[position]
        skeleton.render(ordersList, onClickListener)
        //skeleton.orderCode.text = ordersList.codigo
    }

    override fun getItemCount(): Int {
        return ordersBlocks.size
    }


    fun removeOrder(order: OrdersBlockModel){
        val index = ordersBlocks.indexOf(order)
        ordersBlocks.remove(order)
        notifyItemRemoved(index)
    }

}
