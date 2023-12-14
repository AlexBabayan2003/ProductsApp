package com.example.productsapplication.ui.list

import androidx.lifecycle.ViewModel
import com.example.productsapplication.repo.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    productsRepository: ProductsRepository
): ViewModel() {
    val products = productsRepository.getProducts()
}