package com.riseup.riseup_bussiness.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.databinding.CompletedOrdersBlockRowBinding
import com.riseup.riseup_bussiness.model.OrdersModel

class CompletedOrdersInfoBlocksView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = CompletedOrdersBlockRowBinding.bind(itemView)


    fun render(order : OrdersModel, onClickListener:(OrdersModel) -> Unit, onClickListenerReturn: (OrdersModel) -> Unit){
        binding.completedOrderCodeTV.text = order.code
        binding.ListCardProductsCompleted.text = order.shoppingCar.toString().replace("[","").replace("]","").replace(",","")
        binding.deleteOrderConstraint.setOnClickListener { onClickListener(order) }
        binding.returnOrderConstraint.setOnClickListener { onClickListenerReturn(order) }
        binding.completedOrderCodeTV.setOnClickListener {
            if(binding.ListCardProductsCompleted.visibility == View.VISIBLE){
                binding.OrdersCompleteDividerTitleInfo.visibility = View.GONE
                binding.ListCardProductsCompleted.visibility = View.GONE
            } else {
                binding.ListCardProductsCompleted.visibility = View.VISIBLE
                binding.OrdersCompleteDividerTitleInfo.visibility = View.VISIBLE
            }
        }
    }

}