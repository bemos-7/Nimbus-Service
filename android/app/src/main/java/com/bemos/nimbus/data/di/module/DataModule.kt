package com.bemos.nimbus.data.di.module

import android.content.SharedPreferences
import com.bemos.nimbus.data.remote.local.shared_pref.KeyManagerImpl
import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.data.remote.retrofit.repository.impl.FileServiceRepositoryImpl
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import com.bemos.nimbus.domain.repositories.KeyManagerRepository
import com.bemos.nimbus.domain.use_cases.DeleteFileUseCase
import com.bemos.nimbus.domain.use_cases.DownloadFileUseCase
import com.bemos.nimbus.domain.use_cases.GetKeyUseCase
import com.bemos.nimbus.domain.use_cases.GetListFiles
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.SetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.UploadFileUseCase
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
    fun provideKeyManagerRepository(
        sharedPreferences: SharedPreferences
    ): KeyManagerRepository {
        return KeyManagerImpl(
            sharedPreferences
        )
    }

    @Provides
    fun provideGetKeyUseCase(
        fileServiceRepository: FileServiceRepository
    ): GetKeyUseCase {
        return GetKeyUseCase(
            fileServiceRepository
        )
    }

    @Provides
    fun provideGetListFiles(
        fileServiceRepository: FileServiceRepository
    ): GetListFiles {
        return GetListFiles(
            fileServiceRepository
        )
    }

    @Provides
    fun provideGetSharedKeyUseCase(
        keyManagerRepository: KeyManagerRepository
    ): GetSharedKeyUseCase {
        return GetSharedKeyUseCase(
            keyManagerRepository
        )
    }

    @Provides
    fun provideSetSharedKeyUseCase(
        keyManagerRepository: KeyManagerRepository
    ): SetSharedKeyUseCase {
        return SetSharedKeyUseCase(
            keyManagerRepository
        )
    }

    @Provides
    fun provideDownloadFileUseCase(
        fileServiceRepository: FileServiceRepository
    ): DownloadFileUseCase {
        return DownloadFileUseCase(
            fileServiceRepository
        )
    }

    @Provides
    fun provideUploadFileUseCase(
        fileServiceRepository: FileServiceRepository
    ): UploadFileUseCase {
        return UploadFileUseCase(
            fileServiceRepository
        )
    }

    @Provides
    fun provideDeleteFileUseCase(
        fileServiceRepository: FileServiceRepository
    ): DeleteFileUseCase {
        return DeleteFileUseCase(
            fileServiceRepository
        )
    }
}