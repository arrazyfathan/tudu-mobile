package com.arrazyfathan.tudu.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.EditorialOldFontFamily
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.X
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    ProfileContent(
        onBack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(onBack: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = AppColors.Transparent,
                    ),
                title = {
                    Text(
                        text = "Profile",
                        fontFamily = EditorialOldFontFamily(),
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Italic,
                        fontSize = 18.sp,
                        color = AppColors.Black,
                    )
                },
                navigationIcon = {
                    IconButton(
                        colors =
                            IconButtonDefaults.iconButtonColors(
                                containerColor = AppColors.White,
                            ),
                        onClick = {
                            onBack()
                        },
                    ) {
                        Icon(
                            imageVector = FeatherIcons.ArrowLeft,
                            tint = AppColors.Black,
                            modifier = Modifier.size(18.dp),
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        colors =
                            IconButtonDefaults.iconButtonColors(
                                containerColor = AppColors.White,
                            ),
                        onClick = {
                            onBack()
                        },
                    ) {
                        Icon(
                            imageVector = FeatherIcons.X,
                            tint = AppColors.Black,
                            modifier = Modifier.size(18.dp),
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
        containerColor = AppColors.BgWhite,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {}
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileContent(onBack = {})
}
