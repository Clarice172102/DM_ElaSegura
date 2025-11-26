package com.example.elasegura.ui.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.elasegura.R
import com.example.elasegura.model.MainViewModel
import com.example.elasegura.ui.navigation.BottomNavItem
import com.example.elasegura.ui.theme.ElaSeguraTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    bottomNavItems: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onPrecisoAjuda: () -> Unit = {},
    onAmeacada: () -> Unit = {},
    onEstouBem: () -> Unit = {}
) {
    // Gradiente de fundo
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFCAB0EE),  // Roxo claro
            Color(0xFFCAB0EE),  // Roxo claro
            Color(0xFFCAB0EE),
            Color(0xFFF3E5F5),  // Branco
            Color(0xFFF3E5F5),
            Color(0xFFF3E5F5)
        ),
        startY = 0f,
        endY = 1200f
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFD8B8F8) // lilás conforme o layout
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = item.title,
                                tint = Color(0xFF6A1B9A)
                            )
                        },
                        label = {
                            Text(item.title, color = Color.Black)
                        }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .background(gradient)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            // LOGO
            Image(
                painter = painterResource(id = R.drawable.logo_elasegura1),
                contentDescription = "Logo ElaSegura",
                modifier = Modifier.size(170.dp)
            )

            Spacer(modifier = Modifier.height(90.dp))

            // BOTÃO 1 — PRECISO DE AJUDA
            Button(
                onClick = onPrecisoAjuda,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE53935)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sirene_1),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Preciso de Ajuda", fontSize = 18.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // BOTÃO 2 — ME SINTO AMEAÇADA
            Button(
                onClick = onAmeacada,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEB3B)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.alert_triangle),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Me sinto Ameaçada", fontSize = 18.sp, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // BOTÃO 3 — ESTOU BEM
            Button(
                onClick = onEstouBem,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Estou Bem", fontSize = 18.sp, color = Color.Black)
            }
        }
    }
}