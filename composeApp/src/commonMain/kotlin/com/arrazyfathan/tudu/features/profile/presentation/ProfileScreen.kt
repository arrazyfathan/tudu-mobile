package com.arrazyfathan.tudu.features.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arrazyfathan.tudu.BuildKonfig
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.EditorialOldFontFamily
import com.arrazyfathan.tudu.core.ui.HorizontalFill
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import compose.icons.feathericons.Edit
import compose.icons.feathericons.X
import org.jetbrains.compose.ui.tooling.preview.Preview

const val IMAGE =
    "https://cultivatedculture.com/wp-content/uploads/2019/12/LinkedIn-Profile-Picture-Example-Tynan-Allan.jpeg"

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
                        modifier = Modifier.padding(start = 8.dp),
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
                        modifier = Modifier.padding(end = 8.dp),
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
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState(),
                    ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            VerticalSpacer(16.dp)
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(24.dp),
                        ).background(AppColors.White),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = IMAGE,
                    modifier = Modifier.padding(16.dp).size(50.dp).clip(RoundedCornerShape(50.dp)),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                )

                Column {
                    Text(
                        text = "Alex Richard",
                        style =
                            MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            ),
                        color = AppColors.Black,
                    )
                    Text(
                        text = "alex.richard@mail.com",
                        style =
                            MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Normal,
                                fontSize = 12.sp,
                            ),
                        color = AppColors.Gray,
                    )
                }

                HorizontalFill()

                IconButton(onClick = {}, modifier = Modifier.padding(16.dp)) {
                    Icon(
                        imageVector = FeatherIcons.Edit,
                        tint = AppColors.Black,
                        modifier = Modifier.size(16.dp),
                        contentDescription = "Back",
                    )
                }
            }

            Text(
                text = "Settings",
                style =
                    MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    ),
                color = AppColors.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(24.dp),
                        ).background(AppColors.White),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                VerticalSpacer(spacing = 800.dp)
            }

            Text(
                text = "Support",
                style =
                    MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                    ),
                color = AppColors.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(24.dp),
                        ).background(AppColors.White),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                VerticalSpacer(spacing = 250.dp)
            }

            VerticalSpacer(16.dp)
            Text(
                text = "v.${BuildKonfig.APP_VERSION_NAME}",
                style = MaterialTheme.typography.bodySmall,
                color = AppColors.Black.copy(alpha = .7f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
            VerticalSpacer(16.dp)
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileContent(onBack = {})
}
