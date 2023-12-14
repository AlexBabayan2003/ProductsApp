package com.example.productsapplication.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.productsapplication.data.local.ProductsDao
import com.example.productsapplication.data.remote.ApiService
import com.example.productsapplication.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val productsDao: ProductsDao
) : ProductsRepository {

    override fun getProducts(): Flow<PagingData<Product>> = channelFlow {
        launch {
            Pager(PagingConfig(30)) { productsDao.getAllProducts() }
                .flow.collectLatest {
                    send(it)
                }
        }
        val products = apiService.getProducts().products
        productsDao.insertProducts(*products.toTypedArray())
    }.flowOn(Dispatchers.IO)
}