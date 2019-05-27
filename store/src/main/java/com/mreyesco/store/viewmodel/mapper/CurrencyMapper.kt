package com.mreyesco.store.viewmodel.mapper

import java.text.NumberFormat
import java.util.*

private const val REGEX_EMPTY: String = "\\s"

object CurrencyMapper {

    fun getFormattedCurrency(value: Long): String {
        val locale = Locale.getDefault()
        val formatter = NumberFormat.getCurrencyInstance(locale)
        return formatter.format(value).replace(REGEX_EMPTY.toRegex(), "")
    }
}