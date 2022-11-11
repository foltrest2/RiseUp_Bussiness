package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.riseup.riseup_bussiness.ActiveOrdersFragment
import com.riseup.riseup_bussiness.CompletedOrdersFragment
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.databinding.ActivityOrdersListBinding
import com.riseup.riseup_bussiness.util.ActiveOrdersBlockAdapter
import com.riseup.riseup_bussiness.viewmodel.OrdersListViewModel

class OrdersListActivity : AppCompatActivity() {


    private lateinit var activeOrdersFragment: ActiveOrdersFragment
    private lateinit var completedOrdersFragment: CompletedOrdersFragment

    private val binding: ActivityOrdersListBinding by lazy {
        ActivityOrdersListBinding.inflate(layoutInflater)
    }

     val viewModel:OrdersListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        activeOrdersFragment = ActiveOrdersFragment.newInstance()
        completedOrdersFragment = CompletedOrdersFragment.newInstance()

        viewModel.suscribeRealTimeOrders()


        showFragment(activeOrdersFragment)
        binding.finalizadasDivider.setBackgroundResource(R.color.grayFigma)

        binding.navSuperiorOrdenes.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.activeOrders) {
                showFragment(activeOrdersFragment)
                binding.finalizadasDivider.setBackgroundResource(R.color.grayFigma)
                binding.activasDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
            } else if (menuItem.itemId == R.id.completeOrders){
                showFragment(completedOrdersFragment)
                binding.activasDivider.setBackgroundResource(R.color.grayFigma)
                binding.finalizadasDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
            }
            true
        }

        binding.atrasArrowOrdersBtn.setOnClickListener {
            finish()
            startActivity(Intent(this@OrdersListActivity, ConfigurationActivity::class.java))
        }
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.ordenesContainer, fragment)
        transaction.commit()
    }
}