package com.bemos.nimbus.presentation.list_of_files.utils.ui.download_indicator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bemos.nimbus.R
import com.bemos.nimbus.ui.theme.Green
import com.bemos.nimbus.ui.theme.Red
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun DownloadIndicator(
    isVisible: Boolean,
    onDownloadStatus: Boolean,
    message: String,
    isVisibleChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 500)
            ) + slideInVertically(
                initialOffsetY = { -it },
                animationSpec = tween(durationMillis = 500)
            )
        ) {
            DownloadIndicatorUi(
                isShow = true,
                onSuccess = onDownloadStatus,
                message = message
            )

            LaunchedEffect(isVisible) {
                if (isVisible) {
                    delay(2.seconds)
                    isVisibleChange(false)
                }
            }
        }
    }
}

@Composable
fun DownloadIndicatorUi(
    isShow: Boolean,
    onSuccess: Boolean,
    message: String
) {
    val text = if (onSuccess) message else "Возникла ошибка"
    val icon = if (onSuccess) R.drawable.round_cloud_done_24 else R.drawable.round_cancel_24
    val tint = if (onSuccess) Green else Red

    if (isShow) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF282829)
            )
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
                    fontSize = 18.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(
                        id = icon
                    ),
                    contentDescription = null,
                    tint = tint
                )
            }
        }
    }
}

@Preview
@Composable
private fun DownloadIndicatorPreview() {
    DownloadIndicatorUi(
        isShow = true,
        onSuccess = true,
        message = "Something"
    )
}