package com.bemos.nimbus.presentation.app

import android.app.Application
import com.bemos.nimbus.presentation.di.app_component.AppComponent
import com.bemos.nimbus.presentation.di.app_component.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory()
            .create(this)
    }
}