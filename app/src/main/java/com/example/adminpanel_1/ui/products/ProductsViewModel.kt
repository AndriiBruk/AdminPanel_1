package com.example.adminpanel_1.ui.products

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.adminpanel_1.api.catalogExport.Product
import com.example.adminpanel_1.data.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductsViewModel @ViewModelInject constructor(private val repository: ShopRepository) : ViewModel() {

    val products = repository.getProducts("token").cachedIn(viewModelScope)


//    val login = "test"
//    val password = "12345"
//
//
//    private val currentToken = MutableLiveData<String>()
//
//    private var viewModelJob = Job()
//    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)





//    init {
//        getToken()
//    }
//
//    private fun getToken(){
//        uiScope.launch {
//            currentToken.value = "c1134b7ef4d4d2e21941caa97f928f26"
//            //currentToken.value = repository.getToken(login, password)
//
//        }
//
//    }
//
}