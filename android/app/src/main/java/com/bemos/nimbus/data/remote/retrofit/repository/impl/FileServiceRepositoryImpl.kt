package com.bemos.nimbus.data.remote.retrofit.repository.impl

import android.content.ContentResolver
import android.net.Uri
import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import com.bemos.nimbus.shared.Constants.USER_FOLDER
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream

class FileServiceRepositoryImpl(
    private val api: FileServiceApi
) : FileServiceRepository {
    override suspend fun getKey(): Response<KeyModel> {
        return api.getKey()
    }

    override suspend fun uploadFile(
        userFolderValue: String,
        filePath: String
    ): Response<ResponseBody> {
        TODO("Not yet implemented")
    }

    override suspend fun getListFiles(userFolder: String): Response<List<FileModel>> {
        return api.getListFiles(userFolder)
    }

    fun createMultipartFromUri(contentResolver: ContentResolver, fileUri: Uri): MultipartBody.Part? {
        val mimeType = contentResolver.getType(fileUri) ?: return null

        val tempFile = File.createTempFile("upload", null)
        contentResolver.openInputStream(fileUri)?.use { inputStream ->
            FileOutputStream(tempFile).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }

        val requestFile = RequestBody.create(mimeType.toMediaTypeOrNull(), tempFile)
        return MultipartBody.Part.createFormData("file", tempFile.name, requestFile)
    }
}