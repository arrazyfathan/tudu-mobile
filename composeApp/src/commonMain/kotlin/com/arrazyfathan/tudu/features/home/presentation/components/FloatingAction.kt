package com.arrazyfathan.tudu.features.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.HorizontalSpacer
import com.arrazyfathan.tudu.utils.bounceClick
import compose.icons.FeatherIcons
import compose.icons.feathericons.Plus
import compose.icons.feathericons.Search

sealed class FloatingButton(
    val title: String,
    val icon: ImageVector,
    val color: Color,
    val containerColor: Color,
) {
    data object NewJournal : FloatingButton(
        title = "New Journal",
        icon = FeatherIcons.Plus,
        color = Color.White,
        containerColor = Color.Black,
    )

    data object Search : FloatingButton(
        title = "Search",
        icon = FeatherIcons.Search,
        color = Color.Black,
        containerColor = Color.White,
    )
}

val buttons =
    listOf(
        FloatingButton.NewJournal,
        FloatingButton.Search,
    )

@Composable
fun FloatingActionContainer(
    tabs: List<FloatingButton>,
    onActionSelected: (FloatingButton) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextStyle provides
            LocalTextStyle.current.copy(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
            ),
        LocalContentColor provides AppColors.White,
    ) {
        Row(
            modifier =
                Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(top = 6.dp, bottom = 6.dp, start = 3.dp, end = 3.dp),
        ) {
            for (tab in tabs) {
                HorizontalSpacer(3.dp)
                IconButton(
                    modifier = Modifier.bounceClick().size(54.dp),
                    onClick = { onActionSelected(tab) },
                    colors =
                        IconButtonDefaults.iconButtonColors(
                            containerColor = tab.containerColor,
                            contentColor = tab.color,
                        ),
                ) {
                    Icon(imageVector = tab.icon, contentDescription = tab.title)
                }
                HorizontalSpacer(3.dp)
            }
        }
    }
}
