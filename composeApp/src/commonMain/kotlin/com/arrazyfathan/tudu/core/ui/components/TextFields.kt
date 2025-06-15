package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.arrazyfathan.tudu.core.ui.AppColors
import org.jetbrains.compose.resources.painterResource
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.ic_visibility_off
import tudu.composeapp.generated.resources.ic_visibility_on

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

@Composable
fun PasswordTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = ShapeDefaults.Small,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(placeholder, color = Color.Black.copy(alpha = 0.1f)) },
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyMedium,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
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
        trailingIcon = {
            AnimatedVisibility(text.isNotBlank()) {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter =
                            if (isPasswordVisible) {
                                painterResource(Res.drawable.ic_visibility_on)
                            } else {
                                painterResource(
                                    Res.drawable.ic_visibility_off,
                                )
                            },
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                        tint = AppColors.Gray,
                    )
                }
            }
        },
    )
}
