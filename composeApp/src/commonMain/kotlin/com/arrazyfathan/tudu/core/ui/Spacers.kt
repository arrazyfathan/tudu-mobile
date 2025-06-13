package com.arrazyfathan.tudu.core.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object SpacerSize {
    val Small: Dp = 4.dp
    val Medium: Dp = 8.dp
    val Large: Dp = 16.dp
}

@Composable
fun VerticalSpacer(spacing: Dp = SpacerSize.Medium) {
    Spacer(modifier = Modifier.height(spacing))
}

@Composable
fun HorizontalSpacer(spacing: Dp = SpacerSize.Medium) {
    Spacer(modifier = Modifier.width(spacing))
}
