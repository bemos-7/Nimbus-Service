package com.bemos.nimbus.data.remote.retrofit.api

import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.shared.Constants.USERS_REGISTER
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FileServiceApi {
    @POST(USERS_REGISTER)
    suspend fun getKey() : Response<KeyModel>
}