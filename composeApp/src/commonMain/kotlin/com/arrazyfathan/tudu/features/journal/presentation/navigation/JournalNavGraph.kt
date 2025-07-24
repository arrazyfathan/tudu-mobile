package com.arrazyfathan.tudu.features.journal.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arrazyfathan.tudu.app.navigation.Routes
import com.arrazyfathan.tudu.features.journal.presentation.editor.EditorJournalScreen

fun NavController.navigateToJournal(builder: NavOptionsBuilder.() -> Unit) = navigate(Routes.JournalGraph, builder)

fun NavGraphBuilder.journalGraph(navController: NavHostController) {
    navigation<Routes.JournalGraph>(startDestination = Routes.Journal) {
        composable<Routes.Journal> {
            EditorJournalScreen()
        }
    }
}
