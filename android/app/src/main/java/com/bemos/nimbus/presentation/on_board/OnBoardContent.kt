package com.bemos.nimbus.presentation.on_board

import android.provider.ContactsContract.Directory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bemos.nimbus.R
import com.bemos.nimbus.presentation.on_board.utils.ui.code_view.CodeViewer
import com.bemos.nimbus.presentation.ui.utils.button.CustomButton
import com.bemos.nimbus.ui.theme.BackgroundBlack
import com.bemos.nimbus.ui.theme.BottomSheetBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardContent(
    uuid: String,
    onStartClick: () -> Unit,
    onKeyClick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            containerColor = BottomSheetBlack,
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                CodeViewer(
                    title = "uuid",
                    subtitle = uuid
                )
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }

    Box(
        modifier = Modifier
            .background(
                color = BackgroundBlack
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                onClick = {
                    onStartClick()
                    showBottomSheet = true
                },
                content = "get-key".toUpperCase()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Если у вас есть ключ,",
                color = Color.White,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        showBottomSheet = true
                        onKeyClick()
                    },
                painter = painterResource(
                    id = R.drawable.baseline_vpn_key_24
                ),
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun OnBoardContentPreview() {
    OnBoardContent(
        uuid = "asdf6fa-asdhyuw882-faf2jh2hfh2",
        onStartClick = {},
        onKeyClick = {}
    )
}