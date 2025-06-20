

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun Modifier.safeClickable(
    enabled: Boolean = true,
    debounceTime: Long = 500L,
    interactionSource: MutableInteractionSource,
    indication: Indication? = null,
    onClick: () -> Unit,
): Modifier =
    composed {
        var isClickable by remember { mutableStateOf(true) }
        val scope = rememberCoroutineScope()

        this.clickable(
            enabled = enabled,
            indication = indication,
            interactionSource = interactionSource,
        ) {
            if (isClickable) {
                isClickable = false
                onClick()
                scope.launch {
                    delay(debounceTime)
                    isClickable = true
                }
            }
        }
    }

fun Modifier.clickWithRipple(
    color: Color,
    onClick: () -> Unit,
): Modifier =
    composed {
        this.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(color = color),
            onClick = onClick,
        )
    }
