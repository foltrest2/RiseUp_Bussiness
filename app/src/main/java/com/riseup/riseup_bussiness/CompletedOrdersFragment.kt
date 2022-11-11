package com.riseup.riseup_bussiness

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.riseup.riseup_bussiness.databinding.FragmentCompletedOrdersBinding
import com.riseup.riseup_bussiness.model.OrdersModel
import com.riseup.riseup_bussiness.util.CompletedOrdersBlockAdapter
import com.riseup.riseup_bussiness.view.OrdersListActivity
import com.riseup.riseup_bussiness.viewmodel.OrdersListViewModel
import com.riseup.riseup_users.repo.SharedPreferences


class CompletedOrdersFragment : Fragment() {

    private var _binding: FragmentCompletedOrdersBinding? = null
    private val binding get() = _binding!!

    //Opcion 1 del viewModel
    private val viewModel : OrdersListViewModel by activityViewModels()

    private val adapter = CompletedOrdersBlockAdapter({thisorder -> onItemSelectedRemove(thisorder)}, {thisorder -> onItemSelectedReturn(thisorder)}, {thisorder -> onItemSelectProducts(thisorder)})



    private lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompletedOrdersBinding.inflate(inflater,container,false)
        val view = binding.root

       sp = SharedPreferences(requireContext())

        //Recrear el estado
        var ordersRecycler = binding.recyclerViewCompletedOrders
        ordersRecycler.setHasFixedSize(true)
        ordersRecycler.layoutManager = LinearLayoutManager(activity)
        ordersRecycler.adapter = adapter

        viewModel.orders.observe(viewLifecycleOwner){
            if(it.isNotEmpty()){
                adapter.reset()
                for(orders in it){
                    if (orders.state == 1){
                        adapter.addOrder(orders)
                        //Log.e(">>>", "Aqui me la esta agregando complete")
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

    fun onItemSelectedRemove(order: OrdersModel){
        Toast.makeText(context, "Delete: ${order.code}", Toast.LENGTH_SHORT).show()
        adapter.removeOrder(order)
        viewModel.onOrderStateChange(order,2)
    }

    fun onItemSelectedReturn(order: OrdersModel){
        Toast.makeText(context, "Retorna: ${order.code}", Toast.LENGTH_LONG).show()
        adapter.removeOrder(order)
        viewModel.onOrderStateChange(order,0)
    }
    fun onItemSelectProducts(order: OrdersModel) {
        Toast.makeText(context, "Retorna: ${order.code}\nProductos: ${order.shoppingCar}", Toast.LENGTH_LONG).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = CompletedOrdersFragment()
    }
}