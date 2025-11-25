package com.example.elasegura.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem.PerfilButton,
        BottomNavItem.MapaButton,
        BottomNavItem.ContatosButton
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFD8B8F8) // lilás igual ao seu layout
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route.toString(),
                        onClick = {
                            navController.navigate(item.route.toString()) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                item.icon,
                                contentDescription = item.title,
                                tint = Color(0xFF6A1B9A)
                            )
                        },
                        label = {
                            Text(text = item.title)
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavGraph(navController, modifier = Modifier.padding(padding))
    }
}