package com.example.productsapplication.ui.list

import androidx.recyclerview.widget.DiffUtil
import com.example.productsapplication.entity.Product

class ProductsDiffUtilCallback: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
        oldItem == newItem
}