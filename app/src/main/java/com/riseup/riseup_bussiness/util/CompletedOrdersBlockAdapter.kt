package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.OrdersModel
import com.riseup.riseup_bussiness.view.CompletedOrdersInfoBlocksView
import kotlin.collections.ArrayList

class CompletedOrdersBlockAdapter(private val onClickListener:(OrdersModel) -> Unit, private val onClickListenerReturn:(OrdersModel) -> Unit, private val onClickListenerProducts : (OrdersModel) -> Unit) : RecyclerView.Adapter<CompletedOrdersInfoBlocksView>() {

    private val ordersBlocks = ArrayList<OrdersModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedOrdersInfoBlocksView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.completed_orders_block_row, parent, false)
        val blocksOrdersView = CompletedOrdersInfoBlocksView(row)
        return blocksOrdersView
    }

    override fun onBindViewHolder(skeleton: CompletedOrdersInfoBlocksView, position: Int) {
        val ordersList = ordersBlocks[position]
        skeleton.render(ordersList, onClickListener, onClickListenerReturn, onClickListenerProducts)
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