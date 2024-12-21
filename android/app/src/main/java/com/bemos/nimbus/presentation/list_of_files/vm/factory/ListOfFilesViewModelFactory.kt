package com.bemos.nimbus.presentation.list_of_files.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bemos.nimbus.domain.use_cases.GetListFiles
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.presentation.list_of_files.vm.ListOfFilesViewModel

class ListOfFilesViewModelFactory(
    private val getListFiles: GetListFiles,
    private val getSharedKeyUseCase: GetSharedKeyUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListOfFilesViewModel(
            getListFiles = getListFiles,
            getSharedKeyUseCase = getSharedKeyUseCase
        ) as T
    }
}