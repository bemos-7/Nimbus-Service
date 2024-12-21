package com.bemos.nimbus.presentation.list_of_files.utils.ui.list_item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bemos.nimbus.R
import com.bemos.nimbus.domain.models.FileModel
import com.bemos.nimbus.ui.theme.Blue
import com.bemos.nimbus.ui.theme.BottomSheetBlack
import com.bemos.nimbus.ui.theme.LightBlue
import com.bemos.nimbus.ui.theme.White
import java.io.File

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileItem(
    modifier: Modifier = Modifier,
    file: FileModel,
    onDownloadClick: () -> Unit,
    onLongClick: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .combinedClickable(
                onLongClick = {
                    onLongClick()
                },
                onClick = {},
            )
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Brush.horizontalGradient(listOf(Blue, LightBlue))),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BottomSheetBlack),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(height = 128.dp, width = 60.dp),
                    painter = painterResource(R.drawable.round_insert_drive_file_24),
                    tint = White,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Column {
                    Text(
                        text = file.name,
                        color = Color.White,
                        fontSize = 13.sp,
                        overflow = TextOverflow.Ellipsis,
                        softWrap = false
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = file.sizeMb.toString() + " MB",
                        color = Color.White,
                        fontSize = 13.sp,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Icon(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                expanded = !expanded
                            },
                        painter = painterResource(R.drawable.round_more_vert_24),
                        tint = White,
                        contentDescription = null
                    )

                    CustomExpandedMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = it
                        },
                        onDownloadClick = {

                        },
                        onDeleteClick = {

                        }
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }

    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun CustomExpandedMenu(
    expanded: Boolean,
    onDismissRequest: (Boolean) -> Unit,
    onDownloadClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    DropdownMenu(
        modifier = Modifier
            .background(BottomSheetBlack)
            .border(2.dp, LightBlue, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp)),
        expanded = expanded,
        onDismissRequest = { onDismissRequest(false) },
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DropdownMenuItem(
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Скачать",
                            color = Color.White
                        )
                    }
                },
                onClick = {
                    onDownloadClick()
                    onDismissRequest(false)
                }
            )
            DropdownMenuItem(
                text = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Удалить",
                            color = Color.Red
                        )
                    }
                },
                onClick = {
                    onDeleteClick()
                    onDismissRequest(false)
                }
            )
        }
    }
}

@Preview
@Composable
private fun FileItemPreview() {
    FileItem(
        modifier = Modifier.height(100.dp),
        onDownloadClick = {},
        onLongClick = {},
        file = FileModel(
            name = "SOME",
            sizeMb = 44.0f
        )
    )
}