package com.mreyesco.mobilechallenge

import com.mreyesco.core.CoreModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, CoreModule::class])
@Singleton
interface AppComponent {
}