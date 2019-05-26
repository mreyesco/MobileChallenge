package com.mreyesco.core.networking

import com.google.gson.JsonElement
import io.reactivex.Observable
import retrofit2.http.GET

private const val URL_PRODUCTS = "/bins/4bwec"

interface ProductsApi {
    @GET(URL_PRODUCTS)
    fun getProducts(): Observable<JsonElement>
}