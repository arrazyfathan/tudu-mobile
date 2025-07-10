package com.arrazyfathan.tudu.features.onboarding.presentation.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import safeClickable

data class OnboardingButtonStyle(
    val normalColor: Color = Color(0xFFFFAE1A),
    val pressedColor: Color = Color(0xFFCD8A11),
    val textColor: Color = Color.White,
    val fontSize: TextUnit = 18.sp,
    val cornerRadius: Dp = 100.dp,
    val offset: Dp = 4.dp,
    val horizontalPadding: Dp = 30.dp,
    val verticalPadding: Dp = 16.dp,
    val customIcon: @Composable (() -> Unit)? = null,
)

@Composable
fun OnboardingButton(
    text: String,
    onClick: () -> Unit,
    style: OnboardingButtonStyle = OnboardingButtonStyle(),
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .safeClickable(
                    onClick = onClick,
                    interactionSource = interactionSource,
                    indication = null,
                ).drawWithCache {
                    onDrawBehind {
                        drawRoundRect(
                            color = style.pressedColor,
                            cornerRadius =
                                CornerRadius(
                                    style.cornerRadius.toPx(),
                                    style.cornerRadius.toPx(),
                                ),
                            size =
                                Size(
                                    size.width,
                                    size.height,
                                ),
                        )
                        if (!isPressed) {
                            drawRoundRect(
                                color = style.normalColor,
                                cornerRadius =
                                    CornerRadius(
                                        style.cornerRadius.toPx(),
                                        style.cornerRadius.toPx(),
                                    ),
                                size =
                                    Size(
                                        size.width,
                                        size.height - style.offset.toPx(),
                                    ),
                            )
                        }
                    }
                },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .padding(
                        top = style.verticalPadding,
                        bottom = style.verticalPadding,
                        start = style.horizontalPadding,
                        end = style.horizontalPadding,
                    ).align(Alignment.Center)
                    .offset(
                        x = 0.dp,
                        y = if (isPressed) 0.dp else (-style.offset / 2),
                    ),
        ) {
            Text(
                text = text,
                fontSize = style.fontSize,
                style = MaterialTheme.typography.bodyMedium,
                color = style.textColor,
            )

            Spacer(modifier = Modifier.width(8.dp))

            style.customIcon?.let { icon ->
                icon()
            }
        }
    }
}

@Preview
@Composable
fun ButtonPreview() {
    OnboardingButton(
        text = "Hello",
        onClick = {},
        style = OnboardingButtonStyle(),
        modifier = Modifier,
    )
}
