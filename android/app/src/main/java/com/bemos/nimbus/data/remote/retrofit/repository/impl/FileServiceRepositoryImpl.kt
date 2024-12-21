package com.bemos.nimbus.data.remote.retrofit.repository.impl

import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import retrofit2.Response

class FileServiceRepositoryImpl(
    private val api: FileServiceApi
) : FileServiceRepository {
    override suspend fun getKey(): Response<KeyModel> {
        return api.getKey()
    }
}