package com.example.elasegura.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.elasegura.R
import com.example.elasegura.ui.navigation.Routes

@Composable
fun SplashScreen(navController: NavController) {

    // Delay para trocar de tela
    LaunchedEffect(Unit) {
        delay(1000) // 3 segundos
        navController.navigate(Routes.Home.route) {
            popUpTo(Routes.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_elasegura),
                contentDescription = "Logo ElaSegura"
            )
        }
    }
}