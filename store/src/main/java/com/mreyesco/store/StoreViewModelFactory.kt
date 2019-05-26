package com.mreyesco.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mreyesco.core.repositories.StoreRepository
import javax.inject.Inject

class StoreViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var storeRepository: StoreRepository

    init {
        StoreDependencyManager.storeComponent.inject(this)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            return StoreViewModel(storeRepository) as T
        } else {
            throw IllegalArgumentException("${modelClass.name} is an unknown ViewModel")
        }
    }
}