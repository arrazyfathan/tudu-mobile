package com.arrazyfathan.tudu.features.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
) {
    val viewModel = koinViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(LoginEvent.OnLogin)
    }

    LoginContent(viewModel::onEvent, state)
}

@Composable
fun LoginContent(
    onEvent: (LoginEvent) -> Unit,
    state: LoginState,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Success : ${state.user}")
        Text("isLoading : ${state.isLoading}")
        state.errorMessage?.let { Text(it.asString()) }
    }
}
