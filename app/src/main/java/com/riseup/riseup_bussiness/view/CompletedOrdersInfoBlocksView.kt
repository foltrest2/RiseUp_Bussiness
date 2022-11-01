package com.riseup.riseup_bussiness.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.databinding.CompletedOrdersBlockRowBinding
import com.riseup.riseup_bussiness.model.OrdersModel

class CompletedOrdersInfoBlocksView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = CompletedOrdersBlockRowBinding.bind(itemView)

/**
    var orderCompletedCode : TextView = itemView.findViewById(R.id.completedOrderCodeTV)
    var deleteOrder : TextView = itemView.findViewById(R.id.deleteOrderTV)
    var returnOrder : TextView = itemView.findViewById(R.id.returnOrderTV)
    var constraintOrderDelete : ConstraintLayout = itemView.findViewById(R.id.deleteOrderConstraint)
    var constraintOrderReturn : ConstraintLayout = itemView.findViewById(R.id.returnOrderConstraint)
    var deleteOrderArrow : ImageView = itemView.findViewById(R.id.arrowDeleteOrderImg)
    var returnOrderArrow : ImageView = itemView.findViewById(R.id.arrowReturnOrderImg)
    */

    fun render(order : OrdersModel, onClickListener:(OrdersModel) -> Unit, onClickListenerReturn: (OrdersModel) -> Unit){
        binding.completedOrderCodeTV.text = order.codigo
        binding.deleteOrderConstraint.setOnClickListener { onClickListener(order) }
        binding.returnOrderConstraint.setOnClickListener { onClickListenerReturn(order) }
    }

}