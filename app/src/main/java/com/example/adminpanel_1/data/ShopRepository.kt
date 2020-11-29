package com.example.adminpanel_1.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.adminpanel_1.api.ShopApi
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShopRepository @Inject constructor(private val shopApi: ShopApi) {

    private val currentToken = MutableLiveData<String>()

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private fun getToken(): String {
        uiScope.launch {
            currentToken.value = shopApi.getToken("test", "12345").await().response.token
        }

        return currentToken.value.toString()
    }

     fun getProducts(token: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ShopPagingSource(shopApi, "0ba4683bd37a33213c3481afa29008ed") }
        ).liveData
}