package com.bemos.nimbus.presentation.list_of_files

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bemos.nimbus.presentation.list_of_files.vm.ListOfFilesViewModel
import com.bemos.nimbus.presentation.list_of_files.vm.factory.ListOfFilesViewModelFactory

@Composable
fun ListOfFilesScreen(
    modifier: Modifier = Modifier,
    listOfFilesViewModelFactory: ListOfFilesViewModelFactory
) {
    val viewModel = viewModel<ListOfFilesViewModel>(
        factory = listOfFilesViewModelFactory
    )

    val listFiles by viewModel.fileList.collectAsState()
    val onDownload by viewModel.onDownloadEvent.collectAsState()
    val onUpload by viewModel.onUploadEvent.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getListFiles()
    }

    ListOfFilesContent(
        fileList = listFiles,
        onDownloadSuccess = onDownload,
        onUploadSuccess = onUpload,
        onDownloadClick = {
            viewModel.downloadFile(it)
            viewModel.resetDownloadEvent()
        },
        onDeleteClick = { filename ->
            viewModel.deleteFile(filename)
        },
        onRefresh = {
            viewModel.getListFiles()
        },
        selectedFile = { filePath ->
            viewModel.uploadFile(
                filePath!!
            )
            viewModel.resetUploadEvent()
        }
    )
}