package com.example.elasegura.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.model.Route
import com.example.elasegura.ui.splash.SplashScreen
import com.example.elasegura.ui.splash.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = Route.Splash.route) {

        composable(Route.Splash.route) {
            SplashScreen(navController = navController, viewModel = viewModel, modifier = modifier)
        }

        composable(Route.Home.route) {
            HomeScreen(
                viewModel = viewModel,
                navController = navController,
                bottomNavItems = listOf(
                    BottomNavItem.PerfilButton,
                    BottomNavItem.MapaButton,
                    BottomNavItem.ContatosButton
                )
            )
        }

        composable(Route.Perfil.route) { /* Tela Perfil */ }
        composable(Route.Map.route) { /* Tela Mapa */ }
        composable(Route.Contatos.route) { /* Tela Contatos */ }
    }
}