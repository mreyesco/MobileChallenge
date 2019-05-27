package com.mreyesco.store.di

import com.mreyesco.store.viewmodel.StoreViewModelFactory
import dagger.Subcomponent

@Subcomponent
interface StoreComponent {
    fun inject(factory: StoreViewModelFactory)
}