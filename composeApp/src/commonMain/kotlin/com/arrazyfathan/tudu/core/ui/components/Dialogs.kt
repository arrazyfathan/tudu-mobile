package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.composables.core.Dialog
import com.composables.core.DialogPanel
import com.composables.core.DialogProperties
import com.composables.core.DialogState
import com.composables.core.Scrim

@Composable
fun CustomDialog(
    state: DialogState,
    onDismissRequest: () -> Unit,
    dismissOnBackPress: Boolean = false,
    dismissOnClickOutside: Boolean = false,
    shape: Shape = ShapeDefaults.Medium,
    content: @Composable () -> Unit,
) {
    Dialog(
        state = state,
        onDismiss = onDismissRequest,
        properties =
            DialogProperties(
                dismissOnClickOutside = dismissOnClickOutside,
                dismissOnBackPress = dismissOnBackPress,
            ),
    ) {
        Scrim(enter = fadeIn(), exit = fadeOut())
        DialogPanel(
            modifier =
                Modifier
                    .displayCutoutPadding()
                    .systemBarsPadding()
                    .widthIn(min = 280.dp, max = 500.dp)
                    .padding(20.dp)
                    .clip(shape)
                    .background(AppColors.White),
        ) {
            content()
        }
    }
}

fun DialogState.dismiss() {
    this.visible = false
}

fun DialogState.show() {
    this.visible = true
}
