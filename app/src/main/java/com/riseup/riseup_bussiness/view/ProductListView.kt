package com.riseup.riseup_bussiness.view


import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_bussiness.databinding.ProductsListRowBinding
import com.riseup.riseup_bussiness.model.ProductModel
import java.text.NumberFormat
import java.util.*

class ProductListView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = ProductsListRowBinding.bind(itemView)


    fun render(product: ProductModel, onClickListener:(ProductModel) -> Unit, changePriceListener:(ProductModel) -> Unit,
               changeNameListener:(ProductModel) -> Unit, changeImageListener:(ProductModel) -> Unit){
        binding.productTypeICPLTV.text = product.category
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        binding.productListPriceICTV.text = format.format(product.price)
        binding.productlistnameICTV.text = product.name
        binding.addProductPLICBtn.setOnClickListener { onClickListener(product) }
        binding.productListPriceICTV.setOnClickListener { changePriceListener(product) }
        binding.productlistnameICTV.setOnClickListener { changeNameListener(product) }
        binding.productListICImg.setOnClickListener { changeImageListener(product) }
        val image: Uri = product.image!!.toUri()
        //binding.productListICImg.setImageURI(image)
        Glide.with(itemView).load(image).placeholder(binding.productListICImg.drawable).into(binding.productListICImg);

    }

}