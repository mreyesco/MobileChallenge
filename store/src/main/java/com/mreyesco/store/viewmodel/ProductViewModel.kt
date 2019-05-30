package com.mreyesco.store.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.mreyesco.core.model.ProductModelInterface
import com.mreyesco.store.viewmodel.mapper.CurrencyMapper

internal const val CODE_VOUCHER: String = "VOUCHER"
internal const val CODE_TSHIRT: String = "TSHIRT"
internal const val CODE_MUG: String = "MUG"

class ProductViewModel(val onAmountChanged: () -> Unit) : ViewModel() {
    var code: String = ""
    var rawPrice: Double = 0.0
    val name: ObservableField<String> = ObservableField()
    val price: ObservableField<String> = ObservableField()
    val amount: ObservableInt = ObservableInt()
    val imageUrl: ObservableField<String> = ObservableField()
    val onCart: ObservableBoolean = ObservableBoolean()

    init {
        arrayOf(name, price, imageUrl).forEach { it.set("") }
        amount.set(0)
        onCart.set(false)
        addPropertyCallbacks()
    }

    constructor(product: ProductModelInterface, onAmountChanged: () -> Unit) : this(onAmountChanged) {
        this.rawPrice = product.price
        val price = CurrencyMapper.getFormattedCurrency(rawPrice)
        this.code = product.code
        this.name.set(product.name)
        this.price.set(price)
        this.imageUrl.set(getImageUrl(product.code))
    }

    fun addProductToCart() {
        amount.set(amount.get() + 1)
        onCart.set(true)
    }

    fun removeProductFromCart() {
        val currentAmount = amount.get() - 1
        amount.set(currentAmount)
        if (currentAmount <= 0) {
            amount.set(0)
            onCart.set(false)
        }
    }

    private fun addPropertyCallbacks() {
        val callback= object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                onAmountChanged.invoke()
            }
        }
        amount.addOnPropertyChangedCallback(callback)
    }

    private fun getImageUrl(code: String): String {
        return when (code) {
            CODE_VOUCHER -> "https://loremflickr.com/g/320/240/book"
            CODE_TSHIRT -> "https://loremflickr.com/g/320/240/tshirt"
            CODE_MUG -> "https://loremflickr.com/g/320/240/coffee"
            else -> "https://loremflickr.com/g/320/240/placeholder"
        }
    }

}