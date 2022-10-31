package com.riseup.riseup_bussiness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_bussiness.databinding.FragmentActiveOrdersBinding
import com.riseup.riseup_bussiness.model.OrdersBlockModel
import com.riseup.riseup_bussiness.util.ActiveOrdersBlockAdapter


class ActiveOrdersFragment : Fragment(){

    private var _binding: FragmentActiveOrdersBinding? = null
    private val binding get() = _binding!!

    private val adapter = ActiveOrdersBlockAdapter(){thisorder ->
        onItemSelected(
            thisorder
        )
    }

    //Listener
    var listener: AddCompleteOrder? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActiveOrdersBinding.inflate(inflater, container, false)
        val view = binding.root

        //Recrear el estado
        var ordersRecycler = binding.recyclerViewActiveOrders
        ordersRecycler.setHasFixedSize(true)
        ordersRecycler.layoutManager = LinearLayoutManager(activity)
        ordersRecycler.adapter = adapter

        //ordersRecycler.adapter = adapter

        /**
        listener?.let {
            it.addOrderFromActive()
        }
        */

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemSelected(order: OrdersBlockModel){
        Toast.makeText(context, order.codigo, Toast.LENGTH_SHORT).show()
            adapter.removeOrder(order)

        //Publicacion
        listener?.let {
            it.addOrderFromActive(order)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ActiveOrdersFragment()
    }
    interface AddCompleteOrder{
        fun addOrderFromActive(order : OrdersBlockModel)
    }

}