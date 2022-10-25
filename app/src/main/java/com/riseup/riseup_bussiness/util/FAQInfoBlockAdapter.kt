package com.riseup.riseup_bussiness.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.FAQInfoBlockModel
import com.riseup.riseup_bussiness.view.FAQInfoBlocksView

class FAQInfoBlockAdapter : RecyclerView.Adapter<FAQInfoBlocksView>(){

    private val FAQBlocks = ArrayList<FAQInfoBlockModel>()

    init {

        FAQBlocks.add(FAQInfoBlockModel("¿Qué es RiseUp?","General", "RiseUp es la app que permite a los mejores establecimientos de rumba de la ciudad de Cali fidelizar a sus clientes mientras estos disfrutan de beneficios exclusivos."))
        FAQBlocks.add(FAQInfoBlockModel("¿Cómo usar RiseUp?","General", "RiseUp lo empiezas a usar desde que te registras, para acumular diamantes debes, por lo menos, acudir a las discotecas que hacen parte de la red RiseUp, los establecimientos recompensarán tu asistencia y consumo otorgándote una cantidad de diamantes que luego podrás redimir en consumo de productos de las discotecas."))

    }

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

}