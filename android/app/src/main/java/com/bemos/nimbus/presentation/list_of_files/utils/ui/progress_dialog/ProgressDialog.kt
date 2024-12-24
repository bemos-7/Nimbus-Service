package com.bemos.nimbus.presentation.list_of_files.utils.ui.progress_dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bemos.nimbus.ui.theme.Blue
import com.bemos.nimbus.ui.theme.BottomSheetBlack
import com.bemos.nimbus.ui.theme.LightBlue

@Composable
fun ProgressDialog(
    isShow: Boolean,
    onDismissRequest: () -> Unit
) {
    if (isShow) {
        Dialog(
            onDismissRequest = {
                onDismissRequest()
            },
            content = {
                ProgressDialogUi()
            }
        )
    }
}

@Composable
fun ProgressDialogUi() {
    Card(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Blue),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp)
    ) {
        Column(
            modifier = Modifier
                .background(BottomSheetBlack)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                strokeWidth = 8.dp,
                color = LightBlue,
            )
        }
    }
}

@Preview
@Composable
private fun ProgressDialogPreview() {
    ProgressDialog(
        isShow = true,
        onDismissRequest = {}
    )
}