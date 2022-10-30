package com.riseup.riseup_bussiness.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R

class CompletedOrdersInfoBlocksView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var orderCompletedCode : TextView = itemView.findViewById(R.id.completedOrderCodeTV)
    var deleteOrder : TextView = itemView.findViewById(R.id.deleteOrderTV)
    var returnOrder : TextView = itemView.findViewById(R.id.returnOrderTV)
    var constraintOrderDelete : ConstraintLayout = itemView.findViewById(R.id.deleteOrderConstraint)
    var constraintOrderReturn : ConstraintLayout = itemView.findViewById(R.id.returnOrderConstraint)
    var deleteOrderArrow : ImageView = itemView.findViewById(R.id.arrowDeleteOrderImg)
    var returnOrderArrow : ImageView = itemView.findViewById(R.id.arrowReturnOrderImg)
}