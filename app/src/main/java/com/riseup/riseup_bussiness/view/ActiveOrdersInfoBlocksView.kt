package com.riseup.riseup_bussiness.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.databinding.ActiveOrdersBlockRowBinding
import com.riseup.riseup_bussiness.model.OrdersModel

class ActiveOrdersInfoBlocksView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    val binding = ActiveOrdersBlockRowBinding.bind(itemView)

    fun render(order: OrdersModel, onClickListener: (OrdersModel) -> Unit, onClickListenerProducts:(OrdersModel) -> Unit){
        binding.orderCodeTV.text = order.code
        binding.confirmOrderConstraint.setOnClickListener { onClickListener(order) }
        binding.orderCodeTV.setOnClickListener { onClickListenerProducts(order) }

    }



}