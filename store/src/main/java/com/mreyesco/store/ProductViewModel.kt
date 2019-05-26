package com.mreyesco.store

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mreyesco.core.model.ProductModelInterface

private const val CODE_VOUCHER: String = "VOUCHER"
private const val CODE_TSHIRT: String = "TSHIRT"
private const val CODE_MUG: String = "MUG"

class ProductViewModel() : ViewModel() {
    var code: String = ""
    val name: ObservableField<String> = ObservableField()
    val price: ObservableField<String> = ObservableField()
    val imageUrl: ObservableField<String> = ObservableField()

    init {
        val fields = arrayOf(name, price, imageUrl)
        fields.forEach { it.set("") }
    }

    constructor(product: ProductModelInterface) : this() {
        val price = CurrencyMapper.getFormattedCurrency(product.price)
        this.code = product.code
        this.name.set(product.name)
        this.price.set(price)
        this.imageUrl.set(getImageUrl(product.code))
    }

    private fun getImageUrl(code: String): String {
        return when (code) {
            CODE_VOUCHER -> "https://loremflickr.com/g/320/240/voucher"
            CODE_TSHIRT -> "https://loremflickr.com/g/320/240/tshirt"
            CODE_MUG -> "https://loremflickr.com/g/320/240/mug"
            else -> "https://loremflickr.com/g/320/240/placeholder"
        }
    }

}