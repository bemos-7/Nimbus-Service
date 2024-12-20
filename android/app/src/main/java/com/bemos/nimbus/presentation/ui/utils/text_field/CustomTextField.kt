package com.bemos.nimbus.presentation.ui.utils.text_field

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bemos.nimbus.ui.theme.Black
import com.bemos.nimbus.ui.theme.Blue
import com.bemos.nimbus.ui.theme.LightBlue
import com.bemos.nimbus.ui.theme.White
import com.bemos.nimbus.ui.theme.LightPurple
import com.bemos.nimbus.ui.theme.Purple

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onValueChange: (String) -> Unit,
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    val color by animateColorAsState(
        targetValue = if (isFocused) Purple else White,
        animationSpec = tween(durationMillis = 500)
    )

    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            modifier = Modifier
                .border(
                    width = 3.dp,
                    brush = Brush.horizontalGradient(
                        listOf(
                            color,
                            color
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)
                )
                .clip(RoundedCornerShape(15.dp))
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            value = text,
            onValueChange = onValueChange,
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
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField(
        text = "Password",
        title = "Password",
        onValueChange = {},
    )
}