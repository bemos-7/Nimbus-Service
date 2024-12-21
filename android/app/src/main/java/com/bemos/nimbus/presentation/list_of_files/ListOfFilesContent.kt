package com.bemos.nimbus.presentation.list_of_files

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bemos.nimbus.R
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.presentation.list_of_files.utils.ui.list_item.FileItem
import com.bemos.nimbus.ui.theme.BackgroundBlack
import com.bemos.nimbus.ui.theme.Blue

@Composable
fun ListOfFilesContent(
    modifier: Modifier = Modifier,
    fileList: List<FileModel>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundBlack)
            .padding(top = 50.dp, start = 20.dp, end = 20.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(
                modifier = Modifier
                    .size(55.dp),
                onClick = {

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
                    painter = painterResource(
                        id = R.drawable.round_upload_file_24
                    ),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Column(
            Modifier.fillMaxSize()
        ) {
            LazyColumn(
            ) {
                items(fileList) { file ->
                    FileItem(
                        file = file,
                        onDownloadClick = { },
                        onLongClick = { }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ListOfFilesPreview() {
    ListOfFilesContent(
        fileList = listOf()
    )
}