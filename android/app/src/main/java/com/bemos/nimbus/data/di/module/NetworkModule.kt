package com.bemos.nimbus.data.di.module

import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.shared.Constants.BASE_SERVICE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideFileServiceApi(
        retrofit: Retrofit
    ) : FileServiceApi {
        return retrofit.create(FileServiceApi::class.java)
    }

}