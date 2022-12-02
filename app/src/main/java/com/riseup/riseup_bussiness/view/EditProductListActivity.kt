package com.riseup.riseup_bussiness.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_bussiness.databinding.ActivityEditProductListBinding
import com.riseup.riseup_bussiness.model.DiscoModel
import com.riseup.riseup_bussiness.model.ProductModel
import com.riseup.riseup_bussiness.util.EditProductListAdapter
import com.riseup.riseup_bussiness.viewmodel.EditProductListViewModel

class EditProductListActivity: AppCompatActivity() {

    private lateinit var binding: ActivityEditProductListBinding
    private var products: ArrayList<ProductModel> = arrayListOf()
    private val viewModel : EditProductListViewModel by viewModels()

    private val adapter = EditProductListAdapter({ thisProduct -> onClickListener(thisProduct) },
        { thisProduct -> changePriceListener(thisProduct) },
        { thisProduct -> changeNameListener(thisProduct) },
        { thisProduct -> changeImageListener(thisProduct) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loaded = loadProducts()
        if (loaded != null){
            products = loaded
            Log.e(">>>", "list cargada $products")
            adapter.addAllProducts(products)
        }

        val productsListRecycler = binding.editProductRV
        productsListRecycler.setHasFixedSize(true)
        productsListRecycler.layoutManager = LinearLayoutManager(this)
        productsListRecycler.adapter = adapter
        binding.returnToConfigBtn.setOnClickListener {
            finish()
            startActivity(Intent(this, ConfigurationActivity::class.java))
        }

        binding.addProductWhileEditing.setOnClickListener {
            startActivity(Intent(this, AddProductActivity::class.java))
        }

    }

    private fun onClickListener(thisProduct: ProductModel) {
        Toast.makeText(binding.editProductRV.context, "Producto eliminado", Toast.LENGTH_SHORT).show()
        deleteProduct(thisProduct,products)
        viewModel.deleteProduct(loadUser()!!, thisProduct)
        saveProducts(products)
    }

    private fun changePriceListener(product : ProductModel){
        startActivity(Intent(this@EditProductListActivity, EditProductPriceActivity::class.java).putExtra("Product", product))
    }

    private fun changeNameListener(product : ProductModel){
        startActivity(Intent(this@EditProductListActivity, EditProductNameActivity::class.java).putExtra("Product", product))
    }

    private fun changeImageListener(product : ProductModel){
        startActivity(Intent(this@EditProductListActivity, EditProductImageActivity::class.java).putExtra("Product", product))
    }

    private fun loadProducts(): ArrayList<ProductModel>? {
        val sp = this.getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = sp?.getString("Products", "NO_PRODUCTS")
        return if (json == "NO_PRODUCTS") {
            null
        } else {
            val deserialized = object : TypeToken<ArrayList<ProductModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }

    private fun deleteProduct(thisProduct: ProductModel,products:ArrayList<ProductModel>){
        products.remove(thisProduct)
        adapter.deleteProduct(thisProduct)
    }

    private fun saveProducts(car: ArrayList<ProductModel>) {
        val sp = this.getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = Gson().toJson(car)
        sp?.edit()?.putString("Products", json)?.apply()
    }

    private fun loadUser(): DiscoModel? {
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, DiscoModel::class.java)
        }
    }

}
