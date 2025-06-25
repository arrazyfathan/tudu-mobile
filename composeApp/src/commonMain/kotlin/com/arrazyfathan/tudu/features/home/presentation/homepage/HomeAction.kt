package com.arrazyfathan.tudu.features.home.presentation.homepage

sealed interface HomeAction {
    data object OnCreateNewJournal : HomeAction

    data object OnSearch : HomeAction

    data object OnProfileClick : HomeAction

    data object OnLogout : HomeAction
}
