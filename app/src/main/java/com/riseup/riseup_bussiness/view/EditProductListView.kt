package com.riseup.riseup_bussiness.view


import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riseup.riseup_bussiness.databinding.EditProductsListRowBinding
import com.riseup.riseup_bussiness.model.ProductModel
import java.text.NumberFormat
import java.util.*

class EditProductListView(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding = EditProductsListRowBinding.bind(itemView)


    fun render(product: ProductModel, onClickListener:(ProductModel) -> Unit, changePriceListener:(ProductModel) -> Unit,
               changeNameListener:(ProductModel) -> Unit, changeImageListener:(ProductModel) -> Unit){
        binding.productTypeEPLTV.text = product.category
        val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
        binding.productListPriceEPLTV.text = format.format(product.price)
        binding.productlistnameEPLTV.text = product.name
        binding.addProductPLEPLBtn.setOnClickListener { onClickListener(product) }
        binding.productListPriceEPLTV.setOnClickListener { changePriceListener(product) }
        binding.productlistnameEPLTV.setOnClickListener { changeNameListener(product) }
        binding.productListEPLImg.setOnClickListener { changeImageListener(product) }
        Glide.with(itemView).load(product.imageURL).into(binding.productListEPLImg)
    }

}