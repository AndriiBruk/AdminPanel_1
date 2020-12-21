package com.example.adminpanel_1.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adminpanel_1.R
import com.example.adminpanel_1.api.catalogExport.Product
import com.example.adminpanel_1.databinding.ProductViewItemBinding
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class ProductsPagingAdapter (private val listener: OnItemClickListener):
    PagingDataAdapter<Product, ProductsPagingAdapter.ProductsViewHolder>(DiffCallback) {


    inner class ProductsViewHolder(private val binding: ProductViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        listener.onItemClick(item)
                    }
                }

            }
        }
        fun bind(product: Product) {
            binding.apply {
                articleOverview.text = product.article
                productTitleOverview.text = bindTitle(product)
                productStatusOverview.text = bindStatus(product)

                Glide.with(itemView)
                    .load(bindPhoto(product))
                    .error(R.drawable.ic_empty_photo)
                    .into(productPhotoOverview)
            }
        }

        private fun bindTitle(product: Product): String {
            if (product.title.ru.isEmpty()) {
                return product.mod_title.ru
            }
            return product.title.ru
        }

        private fun bindPhoto(product: Product): String {
            if (product.images.isEmpty()) {
                if (product.gallery_common.isEmpty()) {
                    return ""
                }
                return product.gallery_common[0]
            }

            return product.images[0]
        }

        private fun bindStatus(product: Product):String?{
            return try {
                product.presence.value.ru
            } catch (e: NullPointerException){
                itemView.context.getString(R.string.status_not_choosen)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding =
            ProductViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val currentProduct = getItem(position)
        if (currentProduct != null) {
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