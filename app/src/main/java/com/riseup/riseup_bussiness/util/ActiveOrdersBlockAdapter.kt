package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.OrdersModel
import com.riseup.riseup_bussiness.view.ActiveOrdersInfoBlocksView
import kotlin.collections.ArrayList

class ActiveOrdersBlockAdapter(private val onClickListener:(OrdersModel) -> Unit, private val onClickListenerProducts : (OrdersModel) -> Unit) : RecyclerView.Adapter<ActiveOrdersInfoBlocksView>(){

     private val ordersBlocks = ArrayList<OrdersModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrdersInfoBlocksView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.active_orders_block_row, parent, false)
        val blocksOrdersView = ActiveOrdersInfoBlocksView(row)
        return blocksOrdersView
    }

    override fun onBindViewHolder(skeleton: ActiveOrdersInfoBlocksView, position: Int) {
        val ordersList = ordersBlocks[position]
        skeleton.render(ordersList, onClickListener, onClickListenerProducts)
        //skeleton.orderCode.text = ordersList.codigo
    }

    override fun getItemCount(): Int {
        return ordersBlocks.size
    }


    fun removeOrder(order: OrdersModel){
        val index = ordersBlocks.indexOf(order)
        ordersBlocks.remove(order)
        notifyItemRemoved(index)
    }

    fun addOrder(order : OrdersModel){
            ordersBlocks.add(order)
            notifyItemInserted(ordersBlocks.lastIndex)
    }

    fun addOrderAll(orders : ArrayList<OrdersModel>){
        ordersBlocks.addAll(orders)
        notifyDataSetChanged()
    }

    fun reset(){
        ordersBlocks.clear()
        notifyDataSetChanged()
    }


}
