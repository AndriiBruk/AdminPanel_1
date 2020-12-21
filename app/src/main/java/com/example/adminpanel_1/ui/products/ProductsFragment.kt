package com.example.adminpanel_1.ui.products

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.adminpanel_1.R
import com.example.adminpanel_1.api.catalogExport.Product
import com.example.adminpanel_1.databinding.FragmentProductsBinding
import com.example.adminpanel_1.ui.OverviewLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment(R.layout.fragment_products),
    ProductsPagingAdapter.OnItemClickListener {
    private val viewModel by viewModels<ProductsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProductsBinding.bind(view)

        val adapter = ProductsPagingAdapter(this)

        binding.apply {
            productsList.adapter = adapter.withLoadStateHeaderAndFooter(
                header = OverviewLoadStateAdapter { adapter.retry() },
                footer = OverviewLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener {
                adapter.retry()
            }
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                productsList.isVisible = loadState.source.refresh is LoadState.NotLoading
                textViewError.isVisible = loadState.source.refresh is LoadState.Error
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    productsList.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }


        }

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


    }

    override fun onItemClick(product: Product) {
        val action = ProductsFragmentDirections.actionNavigationProductsToProductDetailsFragment(product)
        findNavController().navigate(action)
    }

}