package com.mreyesco.store.view

import android.app.Activity
import android.app.Dialog
import android.view.Window.FEATURE_NO_TITLE
import com.bumptech.glide.Glide
import com.mreyesco.store.R


class DialogLoading(val context: Activity) {
    private var dialog: Dialog = Dialog(context)

    init {
        with(dialog) {
            requestWindowFeature(FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.custom_loading_layout)
            Glide.with(context)
                .load(R.drawable.loading)
                .into(findViewById(R.id.imageLoading))
        }
    }

    fun showDialog() {
        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss()
    }

}