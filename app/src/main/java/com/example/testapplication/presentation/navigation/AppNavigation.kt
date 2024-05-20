package com.example.testapplication.presentation.navigation

enum class Screen {
    SPLASH,
    HOME,
    PRODUCT_SCREEN,
    ADD_PRODUCT_SCREEN
}

sealed class NavigationItem(val route: String){
    object Splash : NavigationItem(Screen.SPLASH.name)
    object Home : NavigationItem(Screen.HOME.name)
    object ProductScreen : NavigationItem(Screen.PRODUCT_SCREEN.name)
    object AddProductScreen: NavigationItem(Screen.ADD_PRODUCT_SCREEN.name)
}