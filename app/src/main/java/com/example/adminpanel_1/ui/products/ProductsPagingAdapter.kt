package com.example.adminpanel_1.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel_1.api.catalogExport.Product
import com.example.adminpanel_1.databinding.ProductViewItemBinding

class ProductsPagingAdapter : PagingDataAdapter<Product, ProductsPagingAdapter.ProductsViewHolder>(DiffCallback) {


    class ProductsViewHolder (private val binding: ProductViewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product){
            binding.apply {
                articleOverview.text = product.article
                productTitleOverview.text = product.title.ru
                productStatusOverview.text = product.presence.value.ru
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding =ProductViewItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentProduct = getItem(position)
        if (currentProduct != null){
            holder.bind(currentProduct)
        }
    }



    object DiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.article == newItem.article
        }

    }
}