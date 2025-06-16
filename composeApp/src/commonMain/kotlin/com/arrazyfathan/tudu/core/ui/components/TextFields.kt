package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.arrazyfathan.tudu.core.presentation.UiText
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import org.jetbrains.compose.resources.painterResource
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.ic_visibility_off
import tudu.composeapp.generated.resources.ic_visibility_on

@Composable
fun DefaultTextField(
    text: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String,
    shape: CornerBasedShape = ShapeDefaults.Small,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: UiText? = null,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = 1,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = false,
) {
    val isKeyboardTypeNumber =
        keyboardType == KeyboardType.Phone || keyboardType == KeyboardType.Number
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester =
        remember {
            FocusRequester()
        }

    Column {
        TextField(
            value = text,
            onValueChange = { onValueChange(it) },
            placeholder = { Text(placeholder, color = Color.Black.copy(alpha = 0.1f)) },
            maxLines = maxLines,
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            textStyle = MaterialTheme.typography.bodyMedium,
            interactionSource = interactionSource,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                ),
            isError = isError,
            colors =
                TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.Black.copy(alpha = 0.1f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    errorContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                    errorIndicatorColor = Color.Transparent,
                    errorTextColor = MaterialTheme.colorScheme.error,
                    errorCursorColor = MaterialTheme.colorScheme.error,
                ),
            shape = shape,
            modifier = modifier,
        )
        if (isError) {
            VerticalSpacer(4.dp)
            Text(
                text = if (isError) errorMessage?.asString() ?: "" else "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun PasswordTextField(
    text: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    shape: CornerBasedShape = ShapeDefaults.Small,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: UiText? = null,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = 1,
    imeAction: ImeAction = ImeAction.Done,
    singleLine: Boolean = false,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    val isKeyboardTypeNumber =
        keyboardType == KeyboardType.Phone || keyboardType == KeyboardType.Number
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusRequester =
        remember {
            FocusRequester()
        }

    Column {
        TextField(
            value = text,
            onValueChange = { onValueChange(it) },
            placeholder = { Text(placeholder, color = Color.Black.copy(alpha = 0.1f)) },
            maxLines = maxLines,
            singleLine = singleLine,
            leadingIcon = leadingIcon,
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            interactionSource = interactionSource,
            keyboardOptions =
                KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                ),
            isError = isError,
            colors =
                TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.Black.copy(alpha = 0.1f),
                    unfocusedContainerColor = Color.Black.copy(alpha = 0.1f),
                    focusedIndicatorColor = Color.Transparent,
                    disabledTextColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    errorContainerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                    errorIndicatorColor = Color.Transparent,
                    errorTextColor = MaterialTheme.colorScheme.error,
                    errorCursorColor = MaterialTheme.colorScheme.error,
                ),
            shape = shape,
            modifier = modifier,
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

        if (isError) {
            VerticalSpacer(4.dp)
            Text(
                text = if (isError) errorMessage?.asString() ?: "" else "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier,
            )
        }
    }
}
