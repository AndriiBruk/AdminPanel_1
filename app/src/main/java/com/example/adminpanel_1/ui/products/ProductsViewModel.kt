package com.example.adminpanel_1.ui.products

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.adminpanel_1.api.catalogExport.Product
import com.example.adminpanel_1.data.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductsViewModel @ViewModelInject constructor(private val repository: ShopRepository) : ViewModel() {

    val products = repository.getProducts("token").cachedIn(viewModelScope)

}