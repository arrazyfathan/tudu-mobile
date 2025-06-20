package com.arrazyfathan.tudu.features.auth.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arrazyfathan.tudu.core.presentation.ObserveAsEvents
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.core.ui.components.CustomToast
import com.arrazyfathan.tudu.core.ui.components.DefaultButtonWithLoading
import com.arrazyfathan.tudu.core.ui.components.DefaultTextField
import com.arrazyfathan.tudu.core.ui.components.PasswordTextField
import com.arrazyfathan.tudu.utils.removeAllSpaces
import com.arrazyfathan.tudu.utils.toGenericError

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignUp: () -> Unit,
    viewModel: LoginViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { events ->
        when (events) {
            is LoginEvent.Error -> {
                viewModel.showToast()
            }

            LoginEvent.LoginSuccess -> onLoginSuccess()
        }
    }

    if (state.showToast) {
        CustomToast(
            message = state.errorMessage?.asString().toGenericError(),
            durationMillis = 3000,
            onDismiss = { viewModel.dismissToast() },
        )
    }

    LoginContent(
        state = state,
        onAction = viewModel::onAction,
        onSignUpClicked = onSignUp,
    )
}

@Composable
fun LoginContent(
    onAction: (LoginAction) -> Unit,
    state: LoginState,
    onSignUpClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxSize().verticalScroll(rememberScrollState()).imePadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {
        VerticalSpacer(100.dp)
        Text(
            text = "Welcome Back",
            fontSize = 34.sp,
            lineHeight = 38.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = Color.Black,
        )
        VerticalSpacer(8.dp)
        Text(
            text = "Sign Up your account",
            fontSize = 14.sp,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black.copy(alpha = 0.5f),
        )

        VerticalSpacer(100.dp)
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
                onAction(LoginAction.OnUsernameChange(it.removeAllSpaces()))
            },
            placeholder = "Username",
            modifier = Modifier.fillMaxWidth(),
            isError = state.usernameError != null,
            errorMessage = state.usernameError,
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
                onAction(LoginAction.OnPasswordChange(it.removeAllSpaces()))
            },
            placeholder = "Password",
            modifier = Modifier.fillMaxWidth(),
            isError = state.passwordError != null,
            errorMessage = state.passwordError,
        )

        VerticalSpacer(16.dp)

        Text(
            text = "Forget Password?",
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.clickable {},
        )

        VerticalSpacer(16.dp)

        DefaultButtonWithLoading(
            onClick = {
                onAction(LoginAction.OnLogin)
            },
            text = "Login",
            isLoading = state.isLoading,
        )

        VerticalSpacer(8.dp)

        Text(
            text =
                buildAnnotatedString {
                    append("Don't have an account? ")
                    withLink(
                        LinkAnnotation.Clickable(
                            styles =
                                TextLinkStyles(
                                    style = SpanStyle(color = Color.Blue),
                                    hoveredStyle =
                                        SpanStyle(
                                            color = Color.Red,
                                            textDecoration = TextDecoration.Underline,
                                        ),
                                    focusedStyle =
                                        SpanStyle(
                                            color = Color.Gray,
                                        ),
                                    pressedStyle =
                                        SpanStyle(
                                            color = Color.Gray,
                                        ),
                                ),
                            tag = "sign_up",
                            linkInteractionListener = {
                                onSignUpClicked()
                            },
                        ),
                    ) {
                        append("Sign Up")
                    }
                },
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
