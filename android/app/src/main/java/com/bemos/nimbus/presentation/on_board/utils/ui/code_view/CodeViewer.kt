package com.bemos.nimbus.presentation.on_board.utils.ui.code_view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bemos.nimbus.R
import com.bemos.nimbus.ui.theme.BlackBlack
import com.bemos.nimbus.ui.theme.GreenBlack

@Composable
fun CodeViewer(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String
) {
    val clipboardManager = LocalClipboardManager.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = BlackBlack
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = GreenBlack
            ),
            shape = RoundedCornerShape(
                topStart = 5.dp,
                topEnd = 5.dp
            )
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = title.toUpperCase(),
                        color = Color.White
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(16.dp)
                                .clickable {
                                    clipboardManager.setText(AnnotatedString(subtitle))
                                },
                            painter = painterResource(
                                id = R.drawable.baseline_content_copy_24
                            ),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .align(
                    alignment = Alignment.CenterHorizontally
                )
                .padding(
                    vertical = 20.dp,
                    horizontal = 10.dp
                )
        ) {
            Text(
                text = subtitle,
                color = Color.White
            )
        }
    }
}

@Preview()
@Composable
private fun CodeViewerPreview() {
    CodeViewer(
        title = "root",
        subtitle = "something"
    )
}