package com.example.adminpanel_1.ui.products.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.adminpanel_1.R
import com.example.adminpanel_1.databinding.FragmentProductDetailBinding

class ProductDetailsFragment : Fragment(R.layout.fragment_product_detail) {

    private val args by navArgs<ProductDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProductDetailBinding.bind(view)


        val imageAdapter = ImageAdapter(args.product.images)

        binding.apply {
            val product = args.product

            cardTitle.text = product.title.ru

            photoGrid.adapter = imageAdapter

            article.setText(product.article)
            price.setText(product.price.toString())
            oldPrice.setText(product.price_old.toString())

        }


    }
}