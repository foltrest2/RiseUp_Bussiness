package com.riseup.riseup_bussiness.view

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R

class FAQInfoBlocksView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    var blockTitle : TextView = itemView.findViewById(R.id.FAQBlockTitleTV)
    var blockInfo : TextView = itemView.findViewById(R.id.FAQBlockInfoTV)
    var arrowDeployImgBtn : ImageButton = itemView.findViewById(R.id.FAQDeployBlockInfoBtn)
    var dividerBlockFAQ : View = itemView.findViewById(R.id.FAQDividerTitleInfo)
    var constraintBlockFAQ : ConstraintLayout = itemView.findViewById(R.id.FAQblockConstraint)

    fun deployInfoBlockFAQFun (){
        constraintBlockFAQ.setOnClickListener {
            if(!blockInfo.isVisible){
                blockInfo.visibility = View.VISIBLE
                dividerBlockFAQ.visibility = View.VISIBLE
                arrowDeployImgBtn.setImageResource(R.drawable.expand_less24p)
            } else {
                blockInfo.visibility  = View.GONE
                dividerBlockFAQ.visibility = View.GONE
                arrowDeployImgBtn.setImageResource(R.drawable.expand_more24p)
            }

        }
        arrowDeployImgBtn.setOnClickListener {
            if(!blockInfo.isVisible){
                blockInfo.visibility = View.VISIBLE
                dividerBlockFAQ.visibility = View.VISIBLE
                arrowDeployImgBtn.setImageResource(R.drawable.expand_less24p)
            } else {
                blockInfo.visibility  = View.GONE
                dividerBlockFAQ.visibility = View.GONE
                arrowDeployImgBtn.setImageResource(R.drawable.expand_more24p)
            }

        }
    }

}