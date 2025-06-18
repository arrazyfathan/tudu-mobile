package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arrazyfathan.tudu.core.ui.components.DefaultButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomePageScreen() {
    HomePageContent()
}

@Composable
fun HomePageContent() {
    val viewModel = koinViewModel<HomePageViewModel>()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DefaultButton(
            text = "Logout",
            onClick = {
                viewModel.logout()
            },
        )
    }
}
