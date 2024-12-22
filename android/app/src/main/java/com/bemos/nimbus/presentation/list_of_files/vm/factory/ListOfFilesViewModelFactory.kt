package com.bemos.nimbus.presentation.list_of_files.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bemos.nimbus.domain.use_cases.DeleteFileUseCase
import com.bemos.nimbus.domain.use_cases.DownloadFileUseCase
import com.bemos.nimbus.domain.use_cases.GetListFiles
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.UploadFileUseCase
import com.bemos.nimbus.presentation.list_of_files.vm.ListOfFilesViewModel

class ListOfFilesViewModelFactory(
    private val getListFiles: GetListFiles,
    private val getSharedKeyUseCase: GetSharedKeyUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
    private val deleteFileUseCase: DeleteFileUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListOfFilesViewModel(
            getListFiles = getListFiles,
            getSharedKeyUseCase = getSharedKeyUseCase,
            downloadFileUseCase = downloadFileUseCase,
            uploadFileUseCase = uploadFileUseCase,
            deleteFileUseCase = deleteFileUseCase
        ) as T
    }
}