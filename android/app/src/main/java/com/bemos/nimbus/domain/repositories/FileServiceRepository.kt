package com.bemos.nimbus.domain.repositories

import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.shared.Constants.USER_FOLDER
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Query

interface FileServiceRepository {
    suspend fun getKey(): Response<KeyModel>

    suspend fun uploadFile(
        userFolderValue: String,
        filePath: String
    ): Response<ResponseBody>

    suspend fun getListFiles(
        userFolder: String
    ): Response<List<FileModel>>
}