package com.example.adminpanel_1.api

import com.example.adminpanel_1.api.authorization.Auth
import com.example.adminpanel_1.api.catalogExport.CatalogExport
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ShopApi {

    companion object {
        const val BASE_URL = "http://shop28663.horoshop.ua"
    }

    @FormUrlEncoded
    @POST("/api/auth/")
    suspend fun getToken(
            @Field("login") login: String,
            @Field("password") password: String): Deferred<Auth>

    @FormUrlEncoded
    @POST("/api/catalog/export")
    fun getCatalogExport(
            @Field("token") token: String,
            @Field("offset") offset: Int,
            @Field("limit") limit: Int): Deferred<CatalogExport>
}