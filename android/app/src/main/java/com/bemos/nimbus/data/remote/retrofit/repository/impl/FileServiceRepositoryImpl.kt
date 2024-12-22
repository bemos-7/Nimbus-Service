package com.bemos.nimbus.data.remote.retrofit.repository.impl

import android.content.ContentResolver
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.bemos.nimbus.data.remote.retrofit.api.FileServiceApi
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class FileServiceRepositoryImpl(
    private val api: FileServiceApi,
) : FileServiceRepository {
    override suspend fun getKey(): Response<KeyModel> {
        return api.getKey()
    }

    override fun uploadFile(
        userFolder: String,
        filePath: String,
        onComplete: () -> Unit
    ) {
        val file = File(filePath)
        val folder = RequestBody.create("text/plain".toMediaType(), userFolder)

        val fileRequestBody = file.asRequestBody("application/octet-stream".toMediaType())
        val filePart = MultipartBody.Part.createFormData("file", file.name, fileRequestBody)

        val call = api.uploadFile(folder, filePart)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("UplaodFile", "File load successfully!")
                    onComplete()
                } else {
                    Log.e("UploadFile", "Error: ${response.code()} - ${response.message()}")
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("UploadFile", "Downloaded error: ${t.message}")
            }
        })
    }

    override suspend fun getListFiles(userFolder: String): Response<List<FileModel>> {
        return api.getListFiles(userFolder)
    }

    override fun downloadFile(userFolder: String, filename: String) {
        val call = api.downloadFile(
            userFolder = userFolder,
            filename = filename
        )

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful && response.body() != null) {
                    val success = saveFileToDisk(response.body()!!, filename)
                    if (success) {
                        Log.d("DownloadFile", "File downloaded and saved successfully!")
                    } else {
                        Log.e("DownloadFile", "Failed to save file")
                    }
                } else {
                    Log.e("DownloadFile", "Failed to download file: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                Log.e("DownloadFile", "Request failed: ${throwable.message}")
            }
        })
    }

    override fun deleteFile(userFolder: String, filename: String, onComplete: () -> Unit) {
        val call = api.deleteFile(
            userFolder, filename
        )

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, repsonse: Response<Void>) {
                if (repsonse.isSuccessful) {
                    onComplete()
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("DeleteFile", "Error - $t")
            }
        })
    }

    private fun saveFileToDisk(body: ResponseBody, filename: String): Boolean {
        try {
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            if (!downloadsDir.exists()) {
                downloadsDir.mkdirs()
            }

            val file = File(downloadsDir, filename)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null

            try {
                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)

                val buffer = ByteArray(4096)
                var bytesRead: Int

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                }

                outputStream.flush()
                Log.d("FileSave", "File saved to: ${file.absolutePath}")
                return true
            } catch (e: Exception) {
                Log.e("FileSave", "Error saving file: ${e.message}")
                return false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: Exception) {
            Log.e("FileSave", "Error: ${e.message}")
            return false
        }
    }

    private fun createMultipartFromUri(
        contentResolver: ContentResolver,
        fileUri: Uri
    ): MultipartBody.Part? {
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