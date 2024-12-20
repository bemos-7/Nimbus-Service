package com.bemos.nimbus.data.di.module

import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://109.206.236.143:8002/")
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