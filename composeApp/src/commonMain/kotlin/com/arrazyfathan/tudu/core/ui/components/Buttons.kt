package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    onClick: () -> Unit,
    text: String,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black,
    enabled: Boolean = true,
    shape: CornerBasedShape = ShapeDefaults.Small,
) {
    Button(
        modifier = modifier.fillMaxWidth().height(50.dp),
        enabled = enabled,
        onClick = onClick,
        colors =
            ButtonColors(
                containerColor = backgroundColor,
                contentColor = Color.White,
                disabledContainerColor = Color.Black.copy(alpha = 0.5f),
                disabledContentColor = Color.White.copy(alpha = 0.5f),
            ),
        shape = shape,
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = textColor,
        )
    }
}
