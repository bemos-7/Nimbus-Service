package com.bemos.nimbus.domain.use_cases

import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.repositories.FileServiceRepository
import retrofit2.Response

class GetListFiles(
    private val fileServiceRepository: FileServiceRepository
) {
    suspend fun execute(userFolder: String): Response<List<FileModel>> {
        return fileServiceRepository.getListFiles(userFolder)
    }
}