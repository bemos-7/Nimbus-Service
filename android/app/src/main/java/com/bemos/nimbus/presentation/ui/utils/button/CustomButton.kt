package com.bemos.nimbus.presentation.ui.utils.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bemos.nimbus.ui.theme.Black
import com.bemos.nimbus.ui.theme.Blue
import com.bemos.nimbus.ui.theme.Purple

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: String
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(
            width = 2.dp,
            Brush.horizontalGradient(
                listOf(
                    Blue,
                    Blue
                )
            )
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Black
        )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = content,
            fontSize = 24.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun CustomButtonPreview() {
    CustomButton(
        content = "button",
        onClick = {}
    )
}