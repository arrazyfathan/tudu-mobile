package com.arrazyfathan.tudu.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arrazyfathan.tudu.core.ui.AppColors

@Composable
fun TagItem(
    tag: String,
    onClick: (String) -> Unit = {},
) {
    Text(
        text = "#$tag",
        style =
            MaterialTheme.typography.bodySmall.copy(
                color = AppColors.Black.copy(alpha = 0.3f),
                textAlign = TextAlign.Center,
            ),
        modifier =
            Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(50.dp))
                .clickable { onClick(tag) }
                .background(AppColors.Black.copy(alpha = 0.1f))
                .padding(horizontal = 8.dp, vertical = 4.dp),
    )
}
