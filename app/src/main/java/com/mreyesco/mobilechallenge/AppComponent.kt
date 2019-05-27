package com.mreyesco.mobilechallenge

import com.mreyesco.core.di.CoreModule
import com.mreyesco.store.di.StoreComponent
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, CoreModule::class])
@Singleton
interface AppComponent {
    fun plus(): StoreComponent
}