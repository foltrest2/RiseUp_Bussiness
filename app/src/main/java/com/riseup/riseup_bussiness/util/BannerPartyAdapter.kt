package com.riseup.riseup_bussiness.util

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.EventModel
import com.riseup.riseup_bussiness.view.BannerPartyView

class BannerPartyAdapter(private val onClickListener:(EventModel) -> Unit): RecyclerView.Adapter<BannerPartyView>() {

    private val events = ArrayList<EventModel>()


    //Genera un esqueleto gracias al XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPartyView {
        //XML a View
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.banner_party_row, parent, false)
        return BannerPartyView(row)
    }

    //Con el esqueleto ya formado, se le ponen los datos correspondientes al esqueleto
    override fun onBindViewHolder(skeleton: BannerPartyView, position: Int) {
        val event = events[position]
        skeleton.render(event, onClickListener)

    }

    //Este método permite al adaptador saber cuantos elementos se tienen
    override fun getItemCount(): Int {
        return events.size
    }

    fun addEventCard(event : EventModel){
        events.add(event)
        notifyItemInserted(events.lastIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reset(){
        events.clear()
        notifyDataSetChanged()
    }
}