package com.example.productsapplication.repo

import androidx.paging.PagingData
import com.example.productsapplication.entity.Product
import kotlinx.coroutines.flow.Flow


interface ProductsRepository {
    fun getProducts(): Flow<PagingData<Product>>
}