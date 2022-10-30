package com.riseup.riseup_bussiness.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.riseup.riseup_bussiness.ActiveOrdersFragment
import com.riseup.riseup_bussiness.CompletedOrdersFragment
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.databinding.ActivityOrdersListBinding

class OrdersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrdersListBinding
    private lateinit var activeOrdersFragment: ActiveOrdersFragment
    private lateinit var completedOrdersFragment: CompletedOrdersFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        activeOrdersFragment = ActiveOrdersFragment.newInstance()
        completedOrdersFragment = CompletedOrdersFragment.newInstance()

        showFragment(activeOrdersFragment)
        binding.finalizadasDivider.setBackgroundResource(R.color.grayFigma)

        binding.navSuperiorOrdenes.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.activeOrders) {
                showFragment(activeOrdersFragment)
                binding.finalizadasDivider.setBackgroundResource(R.color.grayFigma)
                binding.activasDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
            } else if (menuItem.itemId == R.id.completeOrders){
                binding.activasDivider.setBackgroundResource(R.color.grayFigma)
                binding.finalizadasDivider.setBackgroundResource(R.drawable.gradient_rosado_azul)
                showFragment(completedOrdersFragment)
            }
            true
        }

        /**binding.atrasArrowOrdersBtn.setOnClickListener {

        }
        */
    }

    private fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.ordenesContainer, fragment)
        transaction.commit()
    }
}