package com.riseup.riseup_bussiness.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.databinding.ActiveOrdersBlockRowBinding
import com.riseup.riseup_bussiness.model.OrdersBlockModel

class ActiveOrdersInfoBlocksView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    val binding = ActiveOrdersBlockRowBinding.bind(itemView)
/**
    var orderCode : TextView = itemView.findViewById(R.id.orderCodeTV)
    var confirmOrder : TextView = itemView.findViewById(R.id.confirmOrderTV)
    var constraintOrderConfirm : ConstraintLayout = itemView.findViewById(R.id.confirmOrderConstraint)
    var confirmOrderArrow : ImageView = itemView.findViewById(R.id.arrowConfirmOrderImg)
*/
    fun render(order: OrdersBlockModel, onClickListener: (OrdersBlockModel) -> Unit){
        binding.orderCodeTV.text = order.codigo
        binding.confirmOrderConstraint.setOnClickListener { onClickListener(order) }

    }



}