package com.riseup.riseup_bussiness.view

import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_bussiness.databinding.BannerPartyRowBinding
import com.riseup.riseup_bussiness.model.EventModel

class BannerPartyView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = BannerPartyRowBinding.bind(itemView)


    fun render(event : EventModel, onClickListener:(EventModel) -> Unit){
        binding.constraintBannerCard.setOnClickListener { onClickListener(event) }

        Glide.with(itemView).load(event.posterURL).into(binding.backgroundParty)
    }

}
