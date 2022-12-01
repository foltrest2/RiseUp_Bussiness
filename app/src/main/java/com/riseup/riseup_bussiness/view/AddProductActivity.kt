package com.riseup.riseup_bussiness.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.riseup.riseup_bussiness.databinding.ActivityAddProductBinding
import com.riseup.riseup_bussiness.model.Disco
import com.riseup.riseup_bussiness.model.ProductModel
import com.riseup.riseup_bussiness.viewmodel.InitialConfigViewModel
import java.util.*
import kotlin.collections.ArrayList

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private var products: ArrayList<ProductModel> = arrayListOf()
    private lateinit var productImg: Uri
    private lateinit var user : Disco
    val viewModel: InitialConfigViewModel by viewModels()
   // var listener: OnNewProductListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
         //cargar usuario
         user = loadUser()!!
        //this.listener = ProductListActivity()
        //Log.e(">>>", "listener seteado en addproductactivity: $listener}")
       //Borrar cache
       /*
       val sp = getSharedPreferences("RiseUpBussiness", MODE_PRIVATE)
       sp.edit().clear().apply()
        */
        //Inicializacion de la galeria
        val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::onGalleryResult)
        //Cargar productos
        val temp = loadProducts()
       //Inicializacion del viewModel
       viewModel.setSpUser(user)

       //Listener de la modificacion
       viewModel.inComingUser.observe(this){
           Log.e(">>>", "Actualizado useren observer: ${it}")
           saveUserSp(it)
       }
       viewModel.inComingProduct.observe(this){
           Log.e(">>>", "Actualizado products en observer: ${it}")
           saveProducts(it)
       }

        if(temp!=null){
            products=temp
            viewModel.setSpProducts(products)
        }
        binding.AddProductConfigBtn.setOnClickListener {
           addProduct(products)
            //listener?.let{
                //Log.e(">>>", "se agrega este producto: $products}")
       // }
            saveProducts(products)
        }
        binding.ShowProductPrevViewTV.setOnClickListener {

             finish()
            startActivity(Intent(this,ProductListActivity::class.java))

        }
        binding.ChooseProductImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type= "image/*"
            galleryLauncher.launch(intent)
        }
       binding.FinishInitialConfigBtn.setOnClickListener {

           viewModel.updateDiscoName(user)
           viewModel.updateDiscoRef(user)
           viewModel.updateDiscoListImg(user)
           viewModel.updateDiscoHomeImg(user)
           viewModel.updateDiscoProducts(products,user)

       }
    }
    private fun addProduct(products: ArrayList<ProductModel>){
         if(products.isEmpty()){
             products.add(ProductModel(UUID.randomUUID().toString(),binding.ProductTypeICET.text.toString(),
                 binding.ProductPriceICET.text.toString().toIntOrNull()?:0,
                 0
                 ,binding.ProductNameICET.text.toString(),
                 0,
                 productImg.toString(),
                 ""))

         }else{
             val temporal: ArrayList<ProductModel> = arrayListOf()
             val imgpath = productImg.toString()

             temporal.add(ProductModel(UUID.randomUUID().toString(),binding.ProductTypeICET.text.toString(),
                 binding.ProductPriceICET.text.toString().toIntOrNull()?:0,
                 0
                 ,binding.ProductNameICET.text.toString(),
                 0,
                 imgpath,
                 ""))
             products.addAll(temporal)

         }
    }
    private fun saveProducts(car: ArrayList<ProductModel>) {
        val sp = this.getSharedPreferences("RiseUpBussiness", AppCompatActivity.MODE_PRIVATE)
        val json = Gson().toJson(car)
        sp?.edit()?.putString("Products", json)?.apply()
    }
    private fun loadProducts(): ArrayList<ProductModel>? {
        val sp = this.getSharedPreferences("RiseUpBussiness", AppCompatActivity.MODE_PRIVATE)
        val json = sp?.getString("Products", "NO_USER")
        return if (json == "NO_USER") {
            null
        } else {
            val deserialized = object : TypeToken<ArrayList<ProductModel>>() {}.type
            Gson().fromJson(json, deserialized)
        }
    }
    private fun onGalleryResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK){
            val uriImage = result.data?.data
            productImg = uriImage!!

        }
    }
    private fun loadUser(): Disco? {
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = sp.getString("Usuario", "NO_USER")
        if (json == "NO_USER") {
            return null
        } else {
            return Gson().fromJson(json, Disco::class.java)
        }
    }

    private fun saveUserSp(user: Disco) {
        val sp = getSharedPreferences("RiseUpBusiness", MODE_PRIVATE)
        val json = Gson().toJson(user)
        sp.edit().putString("Usuario", json).apply()
    }
    /*
    interface OnNewProductListener{

        fun onNewProduct(product:ProductModel)

    }
     */
}