package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = ShapeDefaults.Small,
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(placeholder, color = Color.Black.copy(alpha = 0.1f)) },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        colors =
            TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedContainerColor = Color.Black.copy(alpha = 0.1f),
                unfocusedContainerColor = Color.Black.copy(alpha = 0.1f),
                focusedIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black,
            ),
        shape = shape,
        modifier = modifier.fillMaxWidth(),
    )
}
