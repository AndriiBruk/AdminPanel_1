package com.example.adminpanel_1.data

import android.util.Log
import androidx.paging.PagingSource
import com.example.adminpanel_1.api.ShopApi
import com.example.adminpanel_1.api.catalogExport.Product
import retrofit2.HttpException
import java.io.IOException
import kotlin.math.log


class ShopPagingSource(
    private val shopApi: ShopApi,
    private val tokenFetcher: suspend () -> String,
    private val clearToken: () -> Unit
) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {


        val position = params.key ?: 0
        val prductsOffset = position * params.loadSize

//        return try {
//            fetchProducts(prductsOffset, params, position)
//        } catch (e: IOException) {
//            clearToken.invoke()
//            kotlin.runCatching {
//                fetchProducts(prductsOffset, params, position)
//            }.getOrNull()!!
//        } catch (e: HttpException) {
//            LoadResult.Error(e)
//        }

        return try {
            fetchProducts(prductsOffset, params, position)
        } catch (exception: IOException) {
            clearToken.invoke()

            try {
                fetchProducts(prductsOffset, params, position)
            } catch (exception: IOException) {
                LoadResult.Error(exception)
            } catch (exception: HttpException) {
                LoadResult.Error(exception)
            }

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    private suspend fun fetchProducts(
        prductsOffset: Int,
        params: LoadParams<Int>,
        position: Int
    ): LoadResult.Page<Int, Product> {
        val response =
            shopApi.getCatalogExport(tokenFetcher.invoke(), prductsOffset, params.loadSize).response

        val products = response.products

        return LoadResult.Page(
            data = products,
            prevKey = if (position == 0) null else position - 1,
            nextKey = if (products.isEmpty()) null else position + 1
        )
    }
}