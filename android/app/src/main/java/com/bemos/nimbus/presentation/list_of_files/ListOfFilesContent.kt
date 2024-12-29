package com.bemos.nimbus.presentation.list_of_files

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bemos.nimbus.R
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.presentation.list_of_files.utils.ui.download_indicator.DownloadIndicator
import com.bemos.nimbus.presentation.list_of_files.utils.ui.progress_dialog.ProgressDialog
import com.bemos.nimbus.presentation.list_of_files.utils.ui.pull_refresh.CustomPullRefreshLazyColumn
import com.bemos.nimbus.ui.theme.BackgroundBlack
import com.bemos.nimbus.ui.theme.Blue
import java.io.File

@Composable
fun ListOfFilesContent(
    modifier: Modifier = Modifier,
    fileList: List<FileModel>,
    onDownloadSuccess: Boolean?,
    onUploadSuccess: Boolean?,
    onDownloadClick: (String) -> Unit,
    onDeleteClick: (String) -> Unit,
    onRefresh: () -> Unit,
    selectedFile: (String?) -> Unit
) {
    val context = LocalContext.current

    var isRefreshing by remember {
        mutableStateOf(false)
    }
    var isVisibleDownloadStatus by remember {
        mutableStateOf(false)
    }
    var isVisibleUploadStatus by remember {
        mutableStateOf(false)
    }
    var isVisibleProgressBar by remember {
        mutableStateOf(false)
    }
    var onDownloadStatus by remember {
        mutableStateOf(false)
    }
    var onUploadStatus by remember {
        mutableStateOf(false)
    }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedFile(
                getPathFromUri(context, uri)
            )
            isVisibleProgressBar = true
        }
    }

    LaunchedEffect(isRefreshing) {
        if (isRefreshing) {
            isRefreshing = false
        }
    }

    LaunchedEffect(onDownloadSuccess) {
        if (onDownloadSuccess != null) {
            isVisibleDownloadStatus = true
            onDownloadStatus = onDownloadSuccess
        }
    }

    LaunchedEffect(onUploadSuccess) {
        if (onUploadSuccess != null) {
            isVisibleProgressBar = false
            isVisibleUploadStatus = true
            onUploadStatus = onUploadSuccess
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundBlack)
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            Modifier.fillMaxSize()
        ) {
            if (fileList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.3f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(160.dp),
                        painter = painterResource(
                            id = R.drawable.round_insert_drive_file_24
                        ),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Пусто",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            } else {
                CustomPullRefreshLazyColumn(
                    items = fileList,
                    isRefreshing = isRefreshing,
                    onRefresh = {
                        onRefresh()
                        isRefreshing = true
                    },
                    onDownloadClick = onDownloadClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                modifier = Modifier
                    .size(65.dp),
                onClick = {
                    filePickerLauncher.launch(arrayOf("*/*"))
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue,
                ),
                border = BorderStroke(
                    0.dp,
                    color = Blue
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(
                        id = R.drawable.round_upload_file_24
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        DownloadIndicator(
            isVisible = isVisibleDownloadStatus,
            onDownloadStatus = onDownloadStatus,
            message = "Файл сохранен",
            isVisibleChange = {
                isVisibleDownloadStatus = it
            }
        )

        DownloadIndicator(
            isVisible = isVisibleUploadStatus,
            onDownloadStatus = onUploadStatus,
            message = "Файл загружен",
            isVisibleChange = {
                isVisibleUploadStatus = it
            }
        )
    }
    ProgressDialog(
        isShow = isVisibleProgressBar,
        onDismissRequest = {}
    )
}

fun getPathFromUri(context: Context, uri: Uri): String? {
    val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
    context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
            val displayName = cursor.getString(nameIndex)
            val file = File(context.cacheDir, displayName)
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            return file.absolutePath
        }
    }
    return null
}

@Preview
@Composable
private fun ListOfFilesPreview() {
    ListOfFilesContent(
        fileList = listOf(
            FileModel(
                name = "some",
                sizeMb = 12.3f
            )
        ),
        onDownloadSuccess = false,
        onUploadSuccess = false,
        onDeleteClick = {},
        onDownloadClick = {},
        onRefresh = {},
        selectedFile = {}
    )
}