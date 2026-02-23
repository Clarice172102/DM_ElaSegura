package com.example.elasegura.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.ui.splash.AddContactScreen
import com.example.elasegura.ui.splash.ContactsScreen
import com.example.elasegura.ui.splash.HomeScreen
import com.example.elasegura.ui.splash.LoginScreen
import com.example.elasegura.ui.splash.MapScreen
import com.example.elasegura.ui.splash.PerfilScreen
import com.example.elasegura.ui.splash.SplashScreen
import com.example.elasegura.ui.splash.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(navController = navController, startDestination = Route.Splash.route) {

        composable(Route.Splash.route) {
            SplashScreen(navController = navController)
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

        composable(Route.Login.route) {
            LoginScreen(
                navController = navController, // <-- aqui
                onCreateAccountClick = {
                    navController.navigate(Route.Register.route)
                },
                onHelpClick = { }
            )
        }

        composable(Route.Register.route) {
            RegisterScreen(
                onCreateAccount = {
                    navController.navigate(Route.Home.route) {
                        popUpTo(Route.Register.route) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Route.Login.route) }
            )
        }

        composable(Route.Perfil.route) {
            PerfilScreen(navController) }

        composable(Route.Mapa.route) {
            MapScreen(
                navController = navController,
                currentAddress = viewModel.currentAddress.value,
                currentLatLng = viewModel.currentLatLng.value,
                onUpdateLocation = {
                    viewModel.updateLocation(true)
                },
                onShowPeopleAround = { }
            )
        }

        composable(Route.Contatos.route) {
            ContactsScreen(
                navController = navController,
                viewModel = viewModel,
                onAddContact = { navController.navigate("addContact") }
            )
        }

        composable(Route.AddContact.route) {
            AddContactScreen(navController = navController, viewModel = viewModel)
        }

    }
}