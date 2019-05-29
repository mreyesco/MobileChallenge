package com.mreyesco.store.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mreyesco.store.R
import com.mreyesco.store.databinding.FragmentStoreBinding
import com.mreyesco.store.view.ProductsAdapter
import com.mreyesco.store.viewmodel.StoreViewModel
import com.mreyesco.store.viewmodel.StoreViewModelFactory
import kotlinx.android.synthetic.main.fragment_store.*

class StoreFragment : Fragment() {
    private lateinit var storeViewModel: StoreViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity?.let {
            storeViewModel = ViewModelProviders.of(it, StoreViewModelFactory()).get(StoreViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentStoreBinding>(inflater, R.layout.fragment_store, container, false)
        binding.viewModel = storeViewModel
        initViews(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        storeViewModel.getProducts()
    }

    private fun initViews(binding: FragmentStoreBinding) {
        with(binding) {
            recyclerStoreProducts.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ProductsAdapter(arrayListOf())
            }
        }
    }

    private fun setObservers() {
        storeViewModel.products.observe(viewLifecycleOwner, Observer { products ->
            products?.let {
                recyclerStoreProducts.adapter?.let { adapter ->
                    val productsAdapter = adapter as ProductsAdapter
                    productsAdapter.setItems(it)
                }
            }
        })
    }
}