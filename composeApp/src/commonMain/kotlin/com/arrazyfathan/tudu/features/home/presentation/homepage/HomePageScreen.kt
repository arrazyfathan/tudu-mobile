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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrazyfathan.tudu.BuildKonfig
import com.arrazyfathan.tudu.core.ui.AppColors
import com.arrazyfathan.tudu.core.ui.EditorialOldFontFamily
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
import com.composables.core.DialogState
import com.composables.core.rememberDialogState
import compose.icons.FeatherIcons
import compose.icons.feathericons.LogOut
import compose.icons.feathericons.Menu
import compose.icons.feathericons.User
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import tudu.composeapp.generated.resources.Res
import tudu.composeapp.generated.resources.ic_logo_100

expect val blankSpaceItemHeight: Int

@Composable
fun HomePageScreen(
    navigateToProfile: () -> Unit,
    navigateToCreateNewJournal: () -> Unit,
    navigateToSearch: () -> Unit,
    viewModel: HomePageViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    HomePageContent(
        state = state,
        onAction = { action ->
            when (action) {
                HomeAction.OnCreateNewJournal -> navigateToCreateNewJournal()
                HomeAction.OnProfileClick -> navigateToProfile()
                HomeAction.OnSearch -> navigateToSearch()
                else -> Unit
            }
            viewModel.onAction(action)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageContent(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val dialogState = rememberDialogState()
    val hazeState = rememberHazeState()
    val listState = rememberLazyStaggeredGridState()
    val isVisible = rememberSaveable { mutableStateOf(true) }

    val nestedScrollConnection =
        remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset {
                    if (available.y < -1) {
                        isVisible.value = false
                    }

                    if (available.y > 1) {
                        isVisible.value = true
                    }

                    return Offset.Zero
                }
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
            onAction(HomeAction.OnLogout)
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
            DrawerContent(hazeState, dialogState, scope, drawerState)
        }
    }) {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

        Scaffold(
            modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                AnimatedVisibility(
                    visible = isVisible.value,
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
                                FloatingButton.NewJournal -> onAction(HomeAction.OnCreateNewJournal)
                                FloatingButton.Search -> onAction(HomeAction.OnSearch)
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
                LazyVerticalStaggeredGrid(
                    state = listState,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .hazeSource(hazeState)
                            .nestedScroll(nestedScrollConnection),
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    item(span = StaggeredGridItemSpan.FullLine) {
                        VerticalSpacer(blankSpaceItemHeight.dp)
                    }

                    val dummyJournal = dummyJournal
                    items(dummyJournal) { journal ->
                        JournalItem(journal, onClick = {})
                    }

                    item(span = StaggeredGridItemSpan.FullLine) {
                        VerticalSpacer(54.dp)
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
                        IconButton(modifier = Modifier.padding(end = 4.dp), onClick = {
                            onAction(HomeAction.OnProfileClick)
                        }) {
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

@Composable
private fun DrawerContent(
    hazeState: HazeState,
    dialogState: DialogState,
    scope: CoroutineScope,
    drawerState: DrawerState,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .hazeEffect(
                    state = hazeState,
                    style =
                        HazeStyle(
                            backgroundColor = Color.White,
                            tint =
                                HazeTint(
                                    color = AppColors.Black.copy(alpha = 0.7f),
                                ),
                            blurRadius = 30.dp,
                        ),
                ).padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
    ) {
        VerticalSpacer(50.dp)
        Text(
            modifier = Modifier.width(250.dp).padding(start = 16.dp),
            text = "You have a beautiful journal, Ar Razy Fathan Rabbani",
            fontFamily = EditorialOldFontFamily(),
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            fontSize = 30.sp,
            lineHeight = 38.sp,
        )

        VerticalSpacer(50.dp)

        NavigationDrawerItem(
            label = {
                Text(
                    text = "Categories",
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    color = AppColors.White,
                )
            },
            selected = false,
            onClick = {},
            icon = null,
        )

        NavigationDrawerItem(
            label = {
                Text(
                    text = "Tags",
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    color = AppColors.White,
                )
            },
            selected = false,
            onClick = {},
            icon = null,
        )

        NavigationDrawerItem(
            label = {
                Text(
                    text = "Settings",
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    color = AppColors.White,
                )
            },
            selected = false,
            onClick = {},
            icon = null,
        )

        NavigationDrawerItem(
            label = {
                Text(
                    text = "About",
                    style =
                        MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        ),
                    color = AppColors.White,
                )
            },
            selected = false,
            onClick = {},
            icon = null,
        )

        Spacer(modifier = Modifier.weight(1f))

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
        VerticalSpacer(16.dp)
        Text(
            text = "v.${BuildKonfig.APP_VERSION_NAME}",
            style = MaterialTheme.typography.bodySmall,
            color = AppColors.White.copy(alpha = .7f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        VerticalSpacer(16.dp)
    }
}

@Preview
@Composable
fun HomePagePreview() {
    HomePageContent(HomeState(), {})
}
