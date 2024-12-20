package com.bemos.nimbus.data.di.module

import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.data.remote.retrofit.repository.impl.FileServiceRepositoryImpl
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import com.bemos.nimbus.domain.use_cases.GetKeyUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideFileServiceRepository(
        api: FileServiceApi
    ): FileServiceRepository {
        return FileServiceRepositoryImpl(
            api
        )
    }

    @Provides
    fun provideGetKeyUseCase(
        fileServiceRepository: FileServiceRepository
    ) : GetKeyUseCase {
        return GetKeyUseCase(
            fileServiceRepository
        )
    }

}