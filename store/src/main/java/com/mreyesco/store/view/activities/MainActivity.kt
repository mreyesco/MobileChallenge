package com.mreyesco.store.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.mreyesco.store.R
import com.mreyesco.store.databinding.ActivityMainBinding
import com.mreyesco.store.view.DialogLoading

class MainActivity : AppCompatActivity(), ActivityCallback {
    private lateinit var loadingDialog: DialogLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = DialogLoading(this)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController = this.findNavController(R.id.fragmentNavHost)
        navController.setGraph(R.navigation.main_navigation)
    }

    override fun switchLoadingDialog(show: Boolean) {
        with(loadingDialog) {
            if (show) showDialog()
            else hideDialog()
        }
    }
}