package com.bemos.nimbus.domain.use_cases

import com.bemos.nimbus.domain.repositories.FileServiceRepository

class DownloadFileUseCase(
    private val repository: FileServiceRepository
) {
    fun execute(
        userFolder: String,
        filename: String,
    ) {
        repository.downloadFile(
            userFolder,
            filename
        )
    }
}