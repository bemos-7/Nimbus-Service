package com.bemos.nimbus.presentation.list_of_files

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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

    LaunchedEffect(Unit) {
        viewModel.getListFiles()
    }

    ListOfFilesContent(
        fileList = listFiles,
        onDownloadClick = {
            viewModel.downloadFile(it)
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
        }
    )
}