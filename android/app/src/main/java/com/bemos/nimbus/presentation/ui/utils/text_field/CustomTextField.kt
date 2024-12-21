package com.bemos.nimbus.presentation.ui.utils.text_field

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bemos.nimbus.ui.theme.Black
import com.bemos.nimbus.ui.theme.Blue
import com.bemos.nimbus.ui.theme.White
import java.util.Locale

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = Modifier
            .border(
                width = 2.dp,
                brush = Brush.horizontalGradient(
                    listOf(
                        Blue,
                        Blue
                    )
                ),
                shape = RoundedCornerShape(15.dp)
            )
            .clip(RoundedCornerShape(15.dp))
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .then(modifier),
        value = text,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label.toUpperCase(Locale.ROOT),
                color = Color.Gray
            )
        },
        textStyle = TextStyle(
            color = White
        ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedContainerColor = Black,
            unfocusedContainerColor = Black
        )
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField(
        text = "",
        onValueChange = {},
        label = "Key"
    )
}