package com.example.productsapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productsapplication.entity.Product

@Database(
    entities = [Product::class],
    version = 1
)
abstract class ProductsDb : RoomDatabase() {

    abstract fun getProductsDao(): ProductsDao

}