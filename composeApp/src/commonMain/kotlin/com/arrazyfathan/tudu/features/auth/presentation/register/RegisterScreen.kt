package com.arrazyfathan.tudu.features.auth.presentation.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arrazyfathan.tudu.core.presentation.ObserveAsEvents
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.core.ui.components.CustomToast
import com.arrazyfathan.tudu.core.ui.components.DefaultButtonWithLoading
import com.arrazyfathan.tudu.core.ui.components.DefaultTextField
import com.arrazyfathan.tudu.core.ui.components.PasswordTextField
import com.arrazyfathan.tudu.core.ui.components.TuduAppBar
import com.arrazyfathan.tudu.utils.toGenericError
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: RegisterViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { events ->
        when (events) {
            is RegisterEvent.Error -> {
                viewModel.showToast()
            }

            RegisterEvent.RegisterSuccess -> onRegisterSuccess()
        }
    }

    Box {
        RegisterContent(
            onBack = onBack,
            state = state,
            onAction = viewModel::onAction,
        )

        if (state.showToast) {
            CustomToast(
                message = state.errorMessage?.asString().toGenericError(),
                durationMillis = 3000,
                onDismiss = { viewModel.dismissToast() },
            )
        }
    }
}

@Composable
fun RegisterContent(
    onBack: () -> Unit,
    state: RegisterState,
    onAction: (RegisterAction) -> Unit,
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()

    Column(
        modifier =
            Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding(),
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
        Text(
            text = "Register",
            fontSize = 34.sp,
            lineHeight = 38.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
        VerticalSpacer(40.dp)
        Text(
            text = "Username",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
        )
        VerticalSpacer(8.dp)
        DefaultTextField(
            text = state.username,
            onValueChange = {
                onAction(RegisterAction.OnUsernameChange(it))
            },
            placeholder = "Username",
            modifier = Modifier.fillMaxWidth(),
            isError = state.usernameError != null,
            errorMessage = state.usernameError,
        )

        VerticalSpacer(8.dp)

        Text(
            text = "Name",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
        )
        VerticalSpacer(8.dp)
        DefaultTextField(
            text = state.name,
            onValueChange = {
                onAction(RegisterAction.OnNameChange(it))
            },
            placeholder = "Name",
            modifier = Modifier.fillMaxWidth(),
            isError = state.nameError != null,
            errorMessage = state.nameError,
        )

        VerticalSpacer(8.dp)

        VerticalSpacer(8.dp)

        Text(
            text = "Email",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
        )
        VerticalSpacer(8.dp)
        DefaultTextField(
            text = state.email,
            onValueChange = {
                onAction(RegisterAction.OnEmailChange(it))
            },
            placeholder = "Email",
            modifier = Modifier.fillMaxWidth(),
            isError = state.emailError != null,
            errorMessage = state.emailError,
        )

        VerticalSpacer(8.dp)

        Text(
            text = "Password",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
        )
        VerticalSpacer(8.dp)

        PasswordTextField(
            text = state.password,
            onValueChange = {
                onAction(RegisterAction.OnPasswordChange(it))
            },
            placeholder = "Password",
            modifier = Modifier.fillMaxWidth(),
            isError = state.passwordError != null,
            errorMessage = state.passwordError,
        )

        VerticalSpacer(8.dp)

        Text(
            text = "Password Confirmation",
            fontSize = 14.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
        )
        VerticalSpacer(8.dp)

        PasswordTextField(
            text = state.passwordConfirmation,
            onValueChange = {
                onAction(RegisterAction.OnPasswordConfirmationChange(it))
            },
            placeholder = "Password Confirmation",
            modifier = Modifier.fillMaxWidth(),
            isError = state.passwordConfirmationError != null,
            errorMessage = state.passwordConfirmationError,
        )

        VerticalSpacer(24.dp)

        DefaultButtonWithLoading(
            onClick = {
                onAction(RegisterAction.OnRegister)
            },
            text = "Register",
            isLoading = state.isLoading,
        )
    }
}
