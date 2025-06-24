package com.arrazyfathan.tudu.features.home.presentation.homepage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.VerticalSpacer
import com.arrazyfathan.tudu.core.ui.components.CustomDialog
import com.arrazyfathan.tudu.core.ui.components.dismiss
import com.arrazyfathan.tudu.core.ui.components.show
import com.arrazyfathan.tudu.features.home.domain.model.dummyJournal
import com.arrazyfathan.tudu.features.home.presentation.components.DialogLogout
import com.arrazyfathan.tudu.features.home.presentation.components.FloatingActionContainer
import com.arrazyfathan.tudu.features.home.presentation.components.FloatingButton
import com.arrazyfathan.tudu.features.home.presentation.components.JournalItem
import com.arrazyfathan.tudu.features.home.presentation.components.buttons
import com.composables.core.rememberDialogState
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut
import compose.icons.feathericons.Menu
import compose.icons.feathericons.User
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.ic_logo_100

expect val blankSpaceItemHeight: Int

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
    val hazeState = rememberHazeState()
    val listState = rememberLazyListState()

    val previousScrollOffset = remember { mutableStateOf(0) }
    val fabVisible =
        remember {
            derivedStateOf {
                val offset = listState.firstVisibleItemScrollOffset
                val visible = previousScrollOffset.value >= offset || offset == 0
                previousScrollOffset.value = offset
                visible
            }
        }

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
            modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = fabVisible.value,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut(),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .wrapContentWidth()
                                .clip(RoundedCornerShape(50.dp))
                                .hazeEffect(
                                    state = hazeState,
                                    style =
                                        HazeStyle(
                                            backgroundColor = Color.Black,
                                            tint = HazeTint(color = AppColors.White.copy(alpha = 0.1f)),
                                            blurRadius = 30.dp,
                                        ),
                                ).border(
                                    width = 1.5.dp,
                                    brush =
                                        Brush.verticalGradient(
                                            colors =
                                                listOf(
                                                    AppColors.White.copy(alpha = .8f),
                                                    AppColors.White.copy(alpha = .2f),
                                                ),
                                        ),
                                    shape = CircleShape.copy(all = CornerSize(50.dp)),
                                ),
                    ) {
                        FloatingActionContainer(buttons, onActionSelected = { button ->
                            when (button) {
                                FloatingButton.NewJournal -> {}
                                FloatingButton.Search -> {}
                            }
                        })
                    }
                }
            },
            containerColor = AppColors.White,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.hazeSource(hazeState).fillMaxSize(),
                ) {
                    item {
                        VerticalSpacer(blankSpaceItemHeight.dp)
                    }

                    item {
                        LazyVerticalStaggeredGrid(
                            modifier = Modifier.fillMaxWidth().heightIn(max = 2000.dp),
                            columns = StaggeredGridCells.Fixed(2),
                            userScrollEnabled = false,
                            verticalItemSpacing = 8.dp,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            contentPadding = PaddingValues(16.dp),
                        ) {
                            val dummyJournal = dummyJournal
                            items(dummyJournal) { journal ->
                                JournalItem(journal, onClick = {})
                            }
                        }
                    }
                }

                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(
                                brush =
                                    Brush.verticalGradient(
                                        colorStops =
                                            arrayOf(
                                                0.0f to Color.White,
                                                0.5f to Color.White,
                                                0.65f to Color.White,
                                                1.0f to Color.Transparent,
                                            ),
                                    ),
                            ).height(90.dp),
                )

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
                    expandedHeight = 70.dp,
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
            }
        }
    }
}
