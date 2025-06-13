package com.arrazyfathan.tudu.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TuduAppBar(
    title: String? = null,
    icon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth().offset(x = ((-16).dp)),
        title = {
            title?.let { Text(text = it) }
        },
        navigationIcon = {
            if (icon != null) {
                icon()
            }
        },
        colors =
            TopAppBarColors(
                containerColor = Color.White,
                scrolledContainerColor = Color.White,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black,
                actionIconContentColor = Color.Black,
            ),
    )
}
