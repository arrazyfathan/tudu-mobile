package com.arrazyfathan.tudu.features.onboarding.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.features.onboarding.presentation.components.OnboardingButton
import com.arrazyfathan.tudu.features.onboarding.presentation.components.OnboardingButtonStyle
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.bg_onboarding

@Composable
fun OnboardingScreen(
    onNext: () -> Unit
) {
    val viewModel = koinViewModel<OnboardingViewModel>()

    OnboardingContent(
        onEvent = viewModel::onEvent, onNext = onNext
    )
}

@Composable
fun OnboardingContent(
    onEvent: (OnboardingEvent) -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize().background(Color.White)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Discover and capture beauty in your life",
                fontSize = 34.sp,
                lineHeight = 38.sp, style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier.padding(24.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(Res.drawable.bg_onboarding),
                contentDescription = "Illustration",
                alignment = Alignment.Center,
                modifier = Modifier.size(400.dp),
            )
        }

        OnboardingButton(
            text = "Let's Go",
            onClick = {
                onEvent(OnboardingEvent.OnSkipOnboarding)
                onNext()
            },
            style = OnboardingButtonStyle(
                customIcon = {
                    Icon(
                        imageVector = FeatherIcons.ArrowRight,
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                },
                normalColor = Color.Black,
                pressedColor = Color.Black.copy(alpha = 0.7f),
                fontSize = 14.sp
            ),
            modifier = Modifier.width(200.dp).align(Alignment.BottomEnd)
                .padding(bottom = 24.dp, end = 24.dp),
        )
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    OnboardingContent(
        onEvent = {},
        onNext = {},
        modifier = Modifier.background(Color.White),
    )
}
