package com.riseup.riseup_bussiness.util

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.riseup.riseup_bussiness.R
import com.riseup.riseup_bussiness.model.ProductModel
import com.riseup.riseup_bussiness.view.EditProductListView
import com.riseup.riseup_bussiness.view.ProductListView

class EditProductListAdapter(private val onClickListener:(ProductModel) -> Unit,
                             private val changePriceListener:(ProductModel) -> Unit,
                             private val changeNameListener:(ProductModel) -> Unit,
                             private val changeImageListener:(ProductModel) -> Unit): RecyclerView.Adapter<EditProductListView>() {

    private val productList = ArrayList<ProductModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditProductListView {
        //inflate: XML->View
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.edit_products_list_row, parent, false)
        return EditProductListView(view)
    }

    override fun onBindViewHolder(holder: EditProductListView, position: Int) {
        val product = productList[position]
        holder.render(product, onClickListener, changePriceListener, changeNameListener, changeImageListener)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    fun addProduct(product:ProductModel){
        productList.add(product)
        notifyDataSetChanged()
        Log.e(">>>", "producto que se agrega en addproduct en adapter de productlist: $product}")
        Log.e(">>>", "productos que hay agregados en el adapter: $productList}")
    }
    fun addAllProducts(products: List<ProductModel>?){
        Log.e(">>>", "productos agregados: $products}")
        Log.e(">>>", "productos que hay agregados en el adapter: $productList}")
        productList.addAll(products!!)
        notifyItemRangeInserted(0, productList.size)
    }
    fun deleteProduct(product:ProductModel){
        productList.remove(product)
        notifyDataSetChanged()
    }

}