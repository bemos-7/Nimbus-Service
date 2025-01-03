package com.bemos.nimbus.data.remote.retrofit.api

import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.models.KeyModel
import com.bemos.nimbus.shared.Constants.DELETE_FILE
import com.bemos.nimbus.shared.Constants.DOWNLOAD_FILE
import com.bemos.nimbus.shared.Constants.FILE_NAME
import com.bemos.nimbus.shared.Constants.LIST_FILES
import com.bemos.nimbus.shared.Constants.SAVE_FILES
import com.bemos.nimbus.shared.Constants.USERS_REGISTER
import com.bemos.nimbus.shared.Constants.USER_FOLDER
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface FileServiceApi {
    @GET(USERS_REGISTER)
    suspend fun getKey() : Response<KeyModel>

    @Multipart
    @POST(SAVE_FILES)
        fun uploadFile(
        @Part(USER_FOLDER) userFolder: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<Void>

    @GET(LIST_FILES)
    suspend fun getListFiles(
        @Query(USER_FOLDER) userFolder: String
    ): Response<List<FileModel>>

    @GET(DOWNLOAD_FILE)
    fun downloadFile(
        @Query(USER_FOLDER) userFolder: String,
        @Query(FILE_NAME) filename: String
    ): Call<ResponseBody>

    @DELETE(DELETE_FILE)
    fun deleteFile(
        @Query(USER_FOLDER) userFolder: String,
        @Query(FILE_NAME) filename: String
    ): Call<Void>
}