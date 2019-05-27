package com.mreyesco.store.view

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mreyesco.store.R

@BindingAdapter(value = ["amount_added"])
fun setAmountAdded(view: TextView, amount: Int) {
    with(view) {
        text = context.getString(R.string.product_amount_added, amount)
        visibility = if (amount > 0) View.VISIBLE else View.GONE
    }
}