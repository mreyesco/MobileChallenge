package com.mreyesco.core.model


interface ProductModelInterface {
    val code: String
    val name: String
    val price: Double
}

data class ProductModel(
    override val code: String = "",
    override val name: String = "",
    override val price: Double = 0.0
) : ProductModelInterface

