package com.example.adminpanel_1.data

import androidx.paging.PagingSource
import com.example.adminpanel_1.api.ShopApi
import com.example.adminpanel_1.api.catalogExport.Product
import retrofit2.HttpException
import java.io.IOException


class ShopPagingSource(
    private val shopApi: ShopApi,
    private val token: String
) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {


        val position = params.key ?: 0
        val prductsOffset = position * params.loadSize
        return try {
            val response = shopApi.getCatalogExport(token, prductsOffset, params.loadSize).await().response

            val products = response.products

            LoadResult.Page(
                data = products,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (products.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)

        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }
}