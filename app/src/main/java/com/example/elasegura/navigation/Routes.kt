package com.example.elasegura.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Home : Routes("home")
}