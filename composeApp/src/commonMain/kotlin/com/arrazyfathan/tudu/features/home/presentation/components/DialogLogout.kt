package com.arrazyfathan.tudu.features.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.core.ui.components.DefaultButton
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut

@Composable
fun DialogLogout(onLogout: () -> Unit) {
    Column(
        Modifier.width(350.dp).heightIn(min = 300.dp, max = 300.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            VerticalSpacer(24.dp)
            Box(
                modifier =
                    Modifier.size(65.dp).clip(RoundedCornerShape(100.dp)).background(
                        Color.Black.copy(alpha = 0.1f),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = FeatherIcons.LogOut,
                    contentDescription = "Logout",
                    tint = Color.Black,
                )
            }
            VerticalSpacer(24.dp)
            Text("Logout?", style = MaterialTheme.typography.titleMedium, fontSize = 20.sp)
            VerticalSpacer(16.dp)
            Text(
                "Are you sure you want to logout?",
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        color = AppColors.Gray,
                    ),
            )
        }
        DefaultButton(
            shape = RoundedCornerShape(46.dp),
            modifier = Modifier.padding(16.dp),
            onClick = onLogout,
            text = "Yes, logout",
        )
    }
}
