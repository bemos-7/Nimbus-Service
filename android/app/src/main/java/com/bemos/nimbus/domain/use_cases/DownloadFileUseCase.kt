package com.bemos.nimbus.domain.use_cases

import com.bemos.nimbus.domain.repositories.FileServiceRepository

class DownloadFileUseCase(
    private val repository: FileServiceRepository
) {
    fun execute(
        userFolder: String,
        filename: String,
        onComplete: (Boolean) -> Unit
    ) {
        repository.downloadFile(
            userFolder = userFolder,
            filename = filename,
            onComplete = onComplete
        )
    }
}