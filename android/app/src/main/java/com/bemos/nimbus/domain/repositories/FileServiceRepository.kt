package com.bemos.nimbus.domain.repositories

import com.bemos.nimbus.domain.models.KeyModel
import retrofit2.Response

interface FileServiceRepository {
    suspend fun getKey() : Response<KeyModel>
}