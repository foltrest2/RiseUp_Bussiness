package com.riseup.riseup_bussiness

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_bussiness.databinding.FragmentCompletedOrdersBinding
import com.riseup.riseup_bussiness.util.CompletedOrdersBlockAdapter


class CompletedOrdersFragment : Fragment() {

    private var _binding: FragmentCompletedOrdersBinding? = null
    private val binding get() = _binding!!

    private val adapter = CompletedOrdersBlockAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletedOrdersBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recrear el estado
        var ordersRecycler = binding.recyclerViewCompletedOrders
        ordersRecycler.setHasFixedSize(true)
        ordersRecycler.layoutManager = LinearLayoutManager(activity)
        ordersRecycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CompletedOrdersFragment()
    }
}