package com.example.elasegura.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.elasegura.R
import com.example.elasegura.ui.navigation.Route
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1500)
        if (auth.currentUser != null) {
            // Usuário logado → Home
            navController.navigate(Route.Home.route) {
                popUpTo(Route.Splash.route) { inclusive = true }
            }
        } else {
            // Usuário NÃO logado → Login
            navController.navigate(Route.Login.route) {
                popUpTo(Route.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_elasegura),
            contentDescription = null
        )
    }
}