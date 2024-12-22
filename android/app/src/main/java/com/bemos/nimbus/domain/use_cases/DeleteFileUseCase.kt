package com.bemos.nimbus.domain.use_cases

import com.bemos.nimbus.domain.repositories.FileServiceRepository

class DeleteFileUseCase(
    private val repository: FileServiceRepository
) {
    fun execute(
        userFolder: String,
        filename: String,
        onComplete: () -> Unit
    ) {
        repository.deleteFile(
            userFolder,
            filename,
            onComplete
        )
    }
}