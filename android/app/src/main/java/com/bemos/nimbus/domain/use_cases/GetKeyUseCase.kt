package com.bemos.nimbus.domain.use_cases

import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import retrofit2.Response

class GetKeyUseCase(
    private val fileServiceRepository: FileServiceRepository
) {
    suspend fun execute(): Response<KeyModel> {
        return fileServiceRepository.getKey()
    }
}