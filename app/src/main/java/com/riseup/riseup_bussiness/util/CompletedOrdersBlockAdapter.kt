package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.OrdersBlockModel
import com.riseup.riseup_bussiness.view.CompletedOrdersInfoBlocksView
import java.util.*
import kotlin.collections.ArrayList

class CompletedOrdersBlockAdapter : RecyclerView.Adapter<CompletedOrdersInfoBlocksView>() {

    private val ordersBlocks = ArrayList<OrdersBlockModel>()

    init {
        val date = Date(2022, 10, 30)
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"HT3B5",20,0,date,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"HT3B6",20,0,date,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
        ordersBlocks.add(OrdersBlockModel(UUID.randomUUID().toString(),"HT3B7",20,0,date,"1otzuoJuS4ZrQQH6REsL","1XROaNApeL2BBVN1mGmK","tarjeta"))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedOrdersInfoBlocksView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.completed_orders_block_row, parent, false)
        val blocksOrdersView = CompletedOrdersInfoBlocksView(row)
        return blocksOrdersView
    }

    override fun onBindViewHolder(skeleton: CompletedOrdersInfoBlocksView, position: Int) {
        val ordersList = ordersBlocks[position]
        skeleton.orderCompletedCode.text = ordersList.codigo

    }

    override fun getItemCount(): Int {
        return ordersBlocks.size
    }
}