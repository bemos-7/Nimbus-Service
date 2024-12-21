package com.bemos.nimbus.presentation.di.app_component

import android.content.Context
import com.bemos.nimbus.data.di.module.DataModule
import com.bemos.nimbus.data.di.module.LocalModule
import com.bemos.nimbus.data.di.module.NetworkModule
import com.bemos.nimbus.presentation.app.App
import com.bemos.nimbus.presentation.di.module.AppModule
import com.bemos.nimbus.presentation.main_activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        NetworkModule::class,
        LocalModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(
            @BindsInstance
            context: Context
        ): AppComponent
    }
}

val Context.appComponent : AppComponent get() {
    return if (this is App) {
        appComponent
    } else {
        applicationContext.appComponent
    }
}