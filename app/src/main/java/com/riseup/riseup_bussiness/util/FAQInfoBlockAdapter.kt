package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.FAQInfoBlockModel
import com.riseup.riseup_bussiness.view.FAQInfoBlocksView

class FAQInfoBlockAdapter : RecyclerView.Adapter<FAQInfoBlocksView>(){

    private val FAQBlocks = ArrayList<FAQInfoBlockModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FAQInfoBlocksView {
        var inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.faq_info_block_row, parent, false)
        val blocksFAQView = FAQInfoBlocksView(row)
        return blocksFAQView

    }

    override fun onBindViewHolder(skeleton: FAQInfoBlocksView, position: Int) {
        val blocksList = FAQBlocks[position]
        skeleton.blockTitle.text = blocksList.title
        skeleton.blockInfo.text = blocksList.info
        skeleton.deployInfoBlockFAQFun()

    }

    override fun getItemCount(): Int {
        return FAQBlocks.size
    }

    fun addFaq(faq : FAQInfoBlockModel){
        this.FAQBlocks.add(faq)
        notifyItemInserted(FAQBlocks.lastIndex)
    }

    fun reset(){
        FAQBlocks.clear()
        notifyDataSetChanged()
    }

}