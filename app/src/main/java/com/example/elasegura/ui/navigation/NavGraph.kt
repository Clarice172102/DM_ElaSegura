package com.example.elasegura.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.model.Route
import com.example.elasegura.ui.splash.ContactsScreen
import com.example.elasegura.ui.perfil.PerfilScreen
import com.example.elasegura.ui.splash.AddContactScreen
import com.example.elasegura.ui.splash.HomeScreen
import com.example.elasegura.ui.splash.MapScreen
import com.example.elasegura.ui.splash.SplashScreen

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
                    BottomNavItem.HomeButton,
                    BottomNavItem.MapaButton,
                    BottomNavItem.ContatosButton
                )
            )
        }

        composable(Route.Perfil.route) {
            PerfilScreen(navController) }

        composable(Route.Mapa.route) {
            MapScreen(
                navController = navController,
                currentAddress = viewModel.currentAddress.value,
                onUpdateLocation = { viewModel.updateLocation() },
                onShowPeopleAround = { /* lógica para mostrar pessoas */ }
            )
        }

        composable(Route.Contatos.route) {
            ContactsScreen(
                navController = navController,
                viewModel = viewModel,
                onAddContact = { navController.navigate("addContact") }
            )
        }

        composable("addContact") {
            AddContactScreen(navController = navController, viewModel = viewModel)
        }
    }
}