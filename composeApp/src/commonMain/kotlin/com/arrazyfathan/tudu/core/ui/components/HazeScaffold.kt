package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arrazyfathan.tudu.core.ui.AppColors
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@Composable
fun HazeScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    hazeState: HazeState = rememberHazeState(),
    blurTopBar: Boolean = false,
    blurBottomBar: Boolean = false,
    content: @Composable (PaddingValues) -> Unit,
) {
    NestedScaffold(
        modifier = modifier,
        topBar = {
            if (blurTopBar) {
                // We explicitly only want to add a Box if we are blurring.
                // Scaffold has logic which changes based on whether `bottomBar` contains a layout node.
                Box(
                    modifier =
                        Modifier.hazeEffect(
                            state = hazeState,
                            style =
                                HazeStyle(
                                    backgroundColor = Color.Black,
                                    tint = HazeTint(color = AppColors.White.copy(alpha = 0.1f)),
                                    blurRadius = 30.dp,
                                ),
                        ),
                ) {
                    topBar()
                }
            } else {
                topBar()
            }
        },
        bottomBar = {
            if (blurBottomBar) {
                // We explicitly only want to add a Box if we are blurring.
                // Scaffold has logic which changes based on whether `bottomBar` contains a layout node.
                Box(
                    modifier =
                        Modifier.hazeEffect(
                            state = hazeState,
                            style =
                                HazeStyle(
                                    backgroundColor = Color.Black,
                                    tint = HazeTint(color = AppColors.White.copy(alpha = 0.1f)),
                                    blurRadius = 30.dp,
                                ),
                        ),
                ) {
                    bottomBar()
                }
            } else {
                bottomBar()
            }
        },
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) { contentPadding ->
        Box(
            Modifier.hazeSource(hazeState),
        ) {
            content(contentPadding)
        }
    }
}
