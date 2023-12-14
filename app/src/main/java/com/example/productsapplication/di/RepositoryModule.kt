package com.example.productsapplication.di

import android.view.View
import com.example.productsapplication.repo.ProductsRepository
import com.example.productsapplication.repo.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductsRepository(
        repo: ProductsRepositoryImpl
    ): ProductsRepository
}