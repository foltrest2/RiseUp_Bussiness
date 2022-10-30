package com.riseup.riseup_bussiness.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R

class ActiveOrdersInfoBlocksView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    var orderCode : TextView = itemView.findViewById(R.id.orderCodeTV)
    var confirmOrder : TextView = itemView.findViewById(R.id.confirmOrderTV)
    var constraintOrderConfirm : ConstraintLayout = itemView.findViewById(R.id.confirmOrderConstraint)
    var confirmOrderArrow : ImageView = itemView.findViewById(R.id.arrowConfirmOrderImg)



}