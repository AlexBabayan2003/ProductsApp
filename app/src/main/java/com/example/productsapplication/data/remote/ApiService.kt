package com.example.productsapplication.data.remote

import com.example.productsapplication.entity.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = 100,
    ): ProductsResponse
}