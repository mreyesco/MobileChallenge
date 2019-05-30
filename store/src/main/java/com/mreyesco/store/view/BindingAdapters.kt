package com.mreyesco.store.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mreyesco.store.R

@BindingAdapter(value = ["amountAdded"])
fun setAmountAdded(view: TextView, amount: Int) {
    with(view) {
        text = context.getString(R.string.product_amount_added, amount)
        visibility = if (amount > 0) View.VISIBLE else View.GONE
    }
}

@BindingAdapter("app:goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("app:loadImage")
fun loadImage(view: ImageView, url: String) {
    if (url.isNotEmpty()) {
        Glide.with(view.context).load(url).into(view)
    }
}