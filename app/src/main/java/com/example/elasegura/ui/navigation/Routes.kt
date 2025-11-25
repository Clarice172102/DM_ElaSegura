package com.example.elasegura.ui.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Home : Routes("home")
}