package com.example.productsapplication.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.productsapplication.databinding.ItemProductBinding
import com.example.productsapplication.entity.Product

class ProductsAdapter(
    private val onItemClick: (Product) -> Unit
) : PagingDataAdapter<Product, ProductsAdapter.ProductViewHolder>(
    ProductsDiffUtilCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val onItemClick: (Product) -> Unit) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Product) = with(binding) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            tvPrice.text = "$ ${item.price}"
            root.setOnClickListener { onItemClick(item) }
        }
    }

}

