package com.mreyesco.mobilechallenge

import android.app.Application
import com.mreyesco.core.CoreModule
import com.mreyesco.store.StoreDependencyManager

class BaseApplication: Application() {
    lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .coreModule(CoreModule())
            .build()
        StoreDependencyManager.storeComponent = component.plus()
    }
}