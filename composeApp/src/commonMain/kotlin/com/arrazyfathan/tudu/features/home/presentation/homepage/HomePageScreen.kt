package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.core.ui.components.CustomDialog
import com.arrazyfathan.tudu.core.ui.components.DefaultButton
import com.arrazyfathan.tudu.core.ui.components.dismiss
import com.arrazyfathan.tudu.core.ui.components.show
import com.composables.core.rememberDialogState
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomePageScreen() {
    HomePageContent()
}

@Composable
fun HomePageContent() {
    val viewModel = koinViewModel<HomePageViewModel>()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val dialogState = rememberDialogState()

    CustomDialog(
        state = dialogState,
        onDismissRequest = { dialogState.dismiss() },
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        shape = RoundedCornerShape(30.dp),
    ) {
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
                onClick = {
                    viewModel.logout()
                    dialogState.dismiss()
                },
                text = "Yes, logout",
            )
        }
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
            drawerContainerColor = AppColors.White,
            drawerContentColor = Color.Black,
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
            ) {
                VerticalSpacer(50.dp)
                Text(
                    "Tudu",
                    style =
                        MaterialTheme.typography.headlineMedium.copy(
                            color = Color.Black,
                        ),
                )
                HorizontalDivider()
                NavigationDrawerItem(
                    label = { Text("Logout") },
                    selected = false,
                    onClick = {
                        dialogState.show()
                        scope.launch {
                            drawerState.close()
                        }
                    },
                )
            }
        }
    }) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AppColors.White,
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "Home Screen",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                )
            }
        }
    }
}
