package com.example.elasegura.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elasegura.ui.splash.HomeScreen
import com.example.elasegura.ui.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route,
        modifier = modifier
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }

        composable(Routes.Home.route) {
            HomeScreen()
        }

        composable("perfil") {
            // sua tela de perfil aqui
        }

        composable("map") {
            // sua tela de mapa aqui
        }

        composable("contatos") {
            // sua tela de contatos aqui
        }
    }
}