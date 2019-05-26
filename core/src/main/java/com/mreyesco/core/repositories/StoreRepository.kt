package com.mreyesco.core.repositories


import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mreyesco.core.model.ProductModelInterface
import com.mreyesco.core.networking.ProductsApi
import io.reactivex.Observable

class StoreRepository(private val productsApi: ProductsApi) {
    fun getProducts(): Observable<List<ProductModelInterface>> {
        return productsApi.getProducts()
            .map {
                val type = object : TypeToken<List<ProductModelInterface>>() {}.type
                Gson().fromJson<List<ProductModelInterface>>(it, type)
            }
    }
}