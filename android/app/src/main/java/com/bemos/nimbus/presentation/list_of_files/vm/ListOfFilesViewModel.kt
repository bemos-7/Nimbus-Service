package com.bemos.nimbus.presentation.list_of_files.vm

import android.content.ContentResolver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.domain.use_cases.DeleteFileUseCase
import com.bemos.nimbus.domain.use_cases.DownloadFileUseCase
import com.bemos.nimbus.domain.use_cases.GetListFiles
import com.bemos.nimbus.domain.use_cases.GetSharedKeyUseCase
import com.bemos.nimbus.domain.use_cases.UploadFileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfFilesViewModel @Inject constructor(
    private val getListFiles: GetListFiles,
    private val getSharedKeyUseCase: GetSharedKeyUseCase,
    private val downloadFileUseCase: DownloadFileUseCase,
    private val uploadFileUseCase: UploadFileUseCase,
    private val deleteFileUseCase: DeleteFileUseCase
) : ViewModel() {

    private val _fileList = MutableStateFlow(listOf<FileModel>())
    val fileList: StateFlow<List<FileModel>> get() = _fileList

    private val _sharedKey = MutableStateFlow("")

    init {
        getSharedKey()
    }

    fun getListFiles() = viewModelScope.launch {
        val response = getListFiles.execute(_sharedKey.value)
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

    fun deleteFile(
        fileName: String,
    ) {
        deleteFileUseCase.execute(
            userFolder = _sharedKey.value,
            filename = fileName,
            onComplete = {
                getListFiles()
            }
        )
    }

    fun uploadFile(
        filePath: String,
    ) {
        uploadFileUseCase.execute(
            userFolder = _sharedKey.value,
            filePath = filePath,
            onComplete = {
                getListFiles()
            }
        )
    }

    fun downloadFile(fileName: String) {
        downloadFileUseCase.execute(
            _sharedKey.value,
            fileName
        )
    }

    private fun getSharedKey() {
        val key = getSharedKeyUseCase.execute()
        _sharedKey.update {
            key
        }
    }

}