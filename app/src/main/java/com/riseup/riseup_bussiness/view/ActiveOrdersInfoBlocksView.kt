package com.riseup.riseup_bussiness.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.databinding.ActiveOrdersBlockRowBinding
import com.riseup.riseup_bussiness.model.OrdersModel

class ActiveOrdersInfoBlocksView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    val binding = ActiveOrdersBlockRowBinding.bind(itemView)
/**
    var orderCode : TextView = itemView.findViewById(R.id.orderCodeTV)
    var confirmOrder : TextView = itemView.findViewById(R.id.confirmOrderTV)
    var constraintOrderConfirm : ConstraintLayout = itemView.findViewById(R.id.confirmOrderConstraint)
    var confirmOrderArrow : ImageView = itemView.findViewById(R.id.arrowConfirmOrderImg)
*/
    fun render(order: OrdersModel, onClickListener: (OrdersModel) -> Unit){
        binding.orderCodeTV.text = order.code
        binding.confirmOrderConstraint.setOnClickListener { onClickListener(order) }

    }



}