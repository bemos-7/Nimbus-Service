package com.bemos.nimbus.domain.use_cases

import android.content.ContentResolver
import com.bemos.nimbus.domain.repositories.FileServiceRepository

class UploadFileUseCase(
    private val repository: FileServiceRepository
) {
    fun execute(
        userFolder: String,
        filePath: String,
        onComplete: (Boolean) -> Unit
    ) {
        repository.uploadFile(
            userFolder = userFolder,
            filePath = filePath,
            onComplete = onComplete
        )
    }
}