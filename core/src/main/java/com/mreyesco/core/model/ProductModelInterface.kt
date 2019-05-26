package com.mreyesco.core.model


interface ProductModelInterface {
    val code: String
    val name: String
    val price: Long
}

data class ProductModel(
    override val code: String = "",
    override val name: String = "",
    override val price: Long = 0
) : ProductModelInterface

