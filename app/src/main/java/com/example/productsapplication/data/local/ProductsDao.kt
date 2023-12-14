package com.example.productsapplication.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productsapplication.entity.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(vararg product: Product)

    @Query("SELECT * FROM Product ORDER BY price DESC")
    fun getAllProducts(): PagingSource<Int, Product>
}