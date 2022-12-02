package com.riseup.riseup_bussiness.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.databinding.ActiveOrdersBlockRowBinding
import com.riseup.riseup_bussiness.model.OrdersModel
import kotlinx.android.synthetic.main.active_orders_block_row.view.*

class ActiveOrdersInfoBlocksView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    val binding = ActiveOrdersBlockRowBinding.bind(itemView)

    fun render(order: OrdersModel, onClickListener: (OrdersModel) -> Unit){
        binding.orderCodeTV.text = order.code
        binding.ListCardProductsActive.text = order.shoppingCar.toString().replace("[","").replace("]","").replace(",","")
        binding.confirmOrderConstraint.setOnClickListener { onClickListener(order) }
        binding.orderCodeTV.setOnClickListener {
            if(binding.ListCardProductsActive.visibility == View.VISIBLE){
                binding.OrdersActiveDividerTitleInfo.visibility = View.GONE
                binding.ListCardProductsActive.visibility = View.GONE
            } else {
                binding.ListCardProductsActive.visibility = View.VISIBLE
                binding.OrdersActiveDividerTitleInfo.visibility = View.VISIBLE
            }
        }
    }

}