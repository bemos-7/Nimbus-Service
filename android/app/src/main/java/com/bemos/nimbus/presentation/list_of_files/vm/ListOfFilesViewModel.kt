package com.bemos.nimbus.presentation.list_of_files.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.use_cases.GetListFiles
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfFilesViewModel @Inject constructor(
    private val getListFiles: GetListFiles,
    private val getSharedKeyUseCase: GetSharedKeyUseCase
) : ViewModel() {

    private val _fileList = MutableStateFlow(listOf<FileModel>())
    val fileList: StateFlow<List<FileModel>> get() = _fileList

    private val _sharedKey = MutableStateFlow("")
    val sharedKey: StateFlow<String> get() = _sharedKey

    init {
        getSharedKey()
    }

    fun getListFiles(
        userFolder: String
    ) = viewModelScope.launch {
        val response = getListFiles.execute(userFolder)
        if (response.isSuccessful) {
            val list = response.body()
            if (list != null) {
                _fileList.update {
                    list
                }
            } else {
                _fileList.update {
                    listOf()
                }
            }
        }
    }

    private fun getSharedKey() {
        val key = getSharedKeyUseCase.execute()
        _sharedKey.update {
            key
        }
    }

}