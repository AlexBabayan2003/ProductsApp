package com.example.productsapplication.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.productsapplication.R
import com.example.productsapplication.databinding.FragmentProductsBinding
import com.example.productsapplication.ui.details.ProductFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private val viewModel: ProductsViewModel by viewModels()
    private var viewBinding: FragmentProductsBinding? = null
    private val adapter: ProductsAdapter = ProductsAdapter {
        val action = ProductsFragmentDirections.actionProductsFragmentToProductFragment(it)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentProductsBinding
        .inflate(inflater, container, false)
        .also { viewBinding = it }
        .root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.products.onEach { products ->
            adapter.submitData(products)
        }
            .flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)

        viewBinding?.rvProducts?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    companion object {
        fun newInstance() = ProductsFragment()
    }
}