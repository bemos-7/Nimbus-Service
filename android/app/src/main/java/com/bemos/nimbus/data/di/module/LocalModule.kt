package com.bemos.nimbus.data.di.module

import android.content.Context
import android.content.SharedPreferences
import com.bemos.nimbus.shared.Constants.SHARED_PREF_KEY
import dagger.Module
import dagger.Provides

@Module
class LocalModule {

    @Provides
    fun provideSharedPreferences(
        context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

}