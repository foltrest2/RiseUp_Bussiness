package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.OrdersModel
import com.riseup.riseup_bussiness.view.CompletedOrdersInfoBlocksView
import kotlin.collections.ArrayList

class CompletedOrdersBlockAdapter(private val onClickListener:(OrdersModel) -> Unit, private val onClickListenerReturn:(OrdersModel) -> Unit) : RecyclerView.Adapter<CompletedOrdersInfoBlocksView>() {

    private val ordersBlocks = ArrayList<OrdersModel>()

/**
    init {
        val date = Date(2022, 10, 30)
        val carritoItem = CarritoItem(2,"j0WVgXvjZT1h0A7C91Vn")
        val carritoItem2 = CarritoItem(1,"MWGcydbi3aAjfmKpAYr7")
        val productsList : MutableList<CarritoItem> = arrayListOf()
        productsList.add(carritoItem)
        productsList.add(carritoItem2)
        val carritoItems = CarritoModel(productsList)

        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"HT3B5",20,0,date,carritoItems,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"HT3B6",20,0,date,carritoItems,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"HT3B7",20,0,date,carritoItems,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
    }
*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedOrdersInfoBlocksView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.completed_orders_block_row, parent, false)
        val blocksOrdersView = CompletedOrdersInfoBlocksView(row)
        return blocksOrdersView
    }

    override fun onBindViewHolder(skeleton: CompletedOrdersInfoBlocksView, position: Int) {
        val ordersList = ordersBlocks[position]
        skeleton.render(ordersList, onClickListener, onClickListenerReturn)
        //skeleton.orderCompletedCode.text = ordersList.codigo

    }

    override fun getItemCount(): Int {
        return ordersBlocks.size
    }

    fun addOrder(order: OrdersModel) {
        this.ordersBlocks.add(order)
        notifyItemInserted(ordersBlocks.lastIndex)
    }

    fun removeOrder(order: OrdersModel) {
        val index = ordersBlocks.indexOf(order)
        ordersBlocks.remove(order)
        notifyItemRemoved(index)
    }

    fun reset(){
        ordersBlocks.clear()
        notifyDataSetChanged()
    }

}