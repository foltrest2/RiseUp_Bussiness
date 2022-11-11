package com.riseup.riseup_bussiness

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_bussiness.databinding.FragmentActiveOrdersBinding
import com.riseup.riseup_bussiness.model.OrdersModel
import com.riseup.riseup_bussiness.util.ActiveOrdersBlockAdapter
import com.riseup.riseup_bussiness.viewmodel.OrdersListViewModel


class ActiveOrdersFragment : Fragment(){

    private var _binding: FragmentActiveOrdersBinding? = null
    private val binding get() = _binding!!

    //Opcion 1 del viewModel
    private val viewModel : OrdersListViewModel by activityViewModels()


    private val adapter = ActiveOrdersBlockAdapter { thisorder -> onItemSelected(thisorder) }


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


        //Opcion 1 del viewModel
        viewModel.orders.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.reset()
                for(orders in it){
                    if (orders.state == 0){
                        adapter.addOrder(orders)
                    }
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onItemSelected(order: OrdersModel){
            adapter.removeOrder(order)
            viewModel.onOrderStateChange(order,1)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ActiveOrdersFragment()
    }

}