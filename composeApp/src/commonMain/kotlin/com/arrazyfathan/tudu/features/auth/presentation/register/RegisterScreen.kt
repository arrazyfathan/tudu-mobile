package com.arrazyfathan.tudu.features.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arrazyfathan.tudu.core.ui.components.TuduAppBar
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit,
) {
    RegisterContent(
        onBack = onBack,
    )
}

@Composable
fun RegisterContent(onBack: () -> Unit) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        TuduAppBar(
            icon = {
                FilledIconButton(
                    colors =
                        IconButtonDefaults.iconButtonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black,
                        ),
                    onClick = onBack,
                ) {
                    Icon(
                        imageVector = FeatherIcons.ArrowLeft,
                        contentDescription = "Back",
                        tint = Color.Black,
                    )
                }
            },
        )
        Text(text = "Register")
    }
}
