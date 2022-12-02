package com.riseup.riseup_bussiness.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_bussiness.databinding.ActivityProductListBinding
import com.riseup.riseup_bussiness.model.ProductModel
import com.riseup.riseup_bussiness.util.ProductListAdapter

class ProductListActivity : AppCompatActivity() {


    private lateinit var binding: ActivityProductListBinding
    private var products: ArrayList<ProductModel> = arrayListOf()
   // private val viewModel: ProductListViewModel by viewModels()
    //STATE
    private val adapter = ProductListAdapter { thisProduct -> onClickListener(thisProduct) }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        //Cargar productos

        val temp = loadProducts()
        if(temp!=null){
            products = temp
            adapter.addAllProducts(temp)
        }

        Log.e(">>>", "Estos son los productos que hay: $temp}")

        //Recrear el estado
        val productsListRecycler = binding.ProductListICRV
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(this)
        productsListRecycler.adapter = adapter
        binding.returnToLoginICPLButton.setOnClickListener {

            finish()
            startActivity(Intent(this,AddProductActivity::class.java))
        }

        setContentView(binding.root)
    }


    private fun loadProducts(): ArrayList<ProductModel>? {
        val sp = this.getSharedPreferences("RiseUpBusiness", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Products", "NO_USER")
        return if (json == "NO_USER") {
            null
        } else {
            val deserialized = object : TypeToken<ArrayList<ProductModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }

    private fun onClickListener(thisProduct: ProductModel) {
        Toast.makeText(binding.returnToLoginICPLButton.context, "Producto eliminado", Toast.LENGTH_SHORT).show()
          deleteProduct(thisProduct,products)
          saveProducts(products)

    }

    private fun deleteProduct(thisProduct: ProductModel,products:ArrayList<ProductModel>){
        products.remove(thisProduct)
       adapter.deleteProduct(thisProduct)


    }
    private fun saveProducts(car: ArrayList<ProductModel>) {
        val sp = this.getSharedPreferences("RiseUpBusiness", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(car)
        sp?.edit()?.putString("Products", json)?.apply()
    }


}