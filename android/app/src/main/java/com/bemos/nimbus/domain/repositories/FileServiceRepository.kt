package com.bemos.nimbus.domain.repositories

import android.content.ContentResolver
import android.content.Context
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.shared.Constants.DELETE_FILE
import com.bemos.nimbus.shared.Constants.FILE_NAME
import com.bemos.nimbus.shared.Constants.USER_FOLDER
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Query

interface FileServiceRepository {
    suspend fun getKey(): Response<KeyModel>

    fun uploadFile(
        userFolder: String,
        filePath: String,
        onComplete: (Boolean) -> Unit
    )

    suspend fun getListFiles(
        userFolder: String
    ): Response<List<FileModel>>

    fun downloadFile(
        userFolder: String,
        filename: String,
        onComplete: (Boolean) -> Unit
    )

    fun deleteFile(
        userFolder: String,
        filename: String,
        onComplete: () -> Unit
    )
}