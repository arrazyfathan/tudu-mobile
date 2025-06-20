package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.core.ui.components.CustomDialog
import com.arrazyfathan.tudu.core.ui.components.dismiss
import com.arrazyfathan.tudu.core.ui.components.show
import com.arrazyfathan.tudu.features.home.presentation.components.DialogLogout
import com.composables.core.rememberDialogState
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut
import compose.icons.feathericons.Menu
import compose.icons.feathericons.User
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.ic_logo_100

@Composable
fun HomePageScreen() {
    HomePageContent()
}

@OptIn(ExperimentalMaterial3Api::class)
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
        DialogLogout {
            viewModel.logout()
            dialogState.dismiss()
        }
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet(
            drawerShape = RoundedCornerShape(16.dp),
            modifier =
                Modifier
                    .width(350.dp)
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            drawerContainerColor = AppColors.Black,
            drawerContentColor = Color.White,
        ) {
            Column(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
            ) {
                VerticalSpacer(50.dp)
                Text(
                    modifier = Modifier.width(250.dp).padding(start = 16.dp),
                    text = "You have a beautiful journal, Ar Razy Fathan Rabbani",
                    style =
                        MaterialTheme.typography.displaySmall.copy(
                            color = Color.White,
                        ),
                )
                HorizontalDivider(
                    modifier =
                        Modifier.padding(
                            vertical = 24.dp,
                            horizontal = 16.dp,
                        ),
                )
                VerticalSpacer(350.dp)
                NavigationDrawerItem(
                    label = {
                        Text(
                            "Logout",
                            style =
                                MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                ),
                            color = AppColors.White,
                        )
                    },
                    selected = false,
                    onClick = {
                        dialogState.show()
                        scope.launch {
                            drawerState.close()
                        }
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = FeatherIcons.LogOut,
                            contentDescription = "Logout",
                            tint = Color.White,
                        )
                    },
                )
                VerticalSpacer(24.dp)
            }
        }
    }) {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors =
                        TopAppBarDefaults.topAppBarColors(
                            containerColor = AppColors.White,
                            scrolledContainerColor = AppColors.White,
                            navigationIconContentColor = Color.Black,
                            titleContentColor = Color.Black,
                            actionIconContentColor = Color.Black,
                        ),
                    title = {
                        Image(
                            painter = painterResource(Res.drawable.ic_logo_100),
                            contentDescription = "Logo",
                            alignment = Alignment.Center,
                            modifier = Modifier.size(34.dp),
                        )
                    },
                    expandedHeight = 90.dp,
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        IconButton(modifier = Modifier.padding(start = 4.dp), onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = FeatherIcons.Menu,
                                contentDescription = "Menu",
                                tint = Color.Black,
                            )
                        }
                    },
                    actions = {
                        IconButton(modifier = Modifier.padding(end = 4.dp), onClick = {}) {
                            Icon(
                                imageVector = FeatherIcons.User,
                                contentDescription = "Notifications",
                                tint = Color.Black,
                            )
                        }
                    },
                )
            },
            modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
            containerColor = AppColors.White,
        ) { paddingValues ->
            LazyColumn(
                modifier =
                    Modifier.fillMaxSize().padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = 0.dp,
                        start = 16.dp,
                        end = 16.dp,
                    ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(50) {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    ) {
                        Text(
                            modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp),
                            text = "Home Screen",
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black),
                        )
                    }
                }
            }
        }
    }
}
