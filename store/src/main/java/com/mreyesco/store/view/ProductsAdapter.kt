package com.mreyesco.store.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mreyesco.store.R
import com.mreyesco.store.databinding.ItemProductBinding
import com.mreyesco.store.viewmodel.ProductViewModel

class ProductsAdapter(private val products: ArrayList<ProductViewModel>) : RecyclerView.Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemProductBinding>(inflater, R.layout.item_product, parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun setItems(items: List<ProductViewModel>) {
        this.products.clear()
        this.products.addAll(items)
        this.notifyDataSetChanged()
    }
}

class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductViewModel) {
        binding.viewModel = product
    }
}