package com.example.elasegura.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.elasegura.model.MainViewModel

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel() // pega o ViewModel

    val items = listOf(
        BottomNavItem.PerfilButton,
        BottomNavItem.MapaButton,
        BottomNavItem.ContatosButton
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFD8B8F8) // lilás igual ao layout
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute?.equals(item.route) == true
                        ,
                        onClick = {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
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
        // Passa o viewModel e o modifier corretamente para o NavGraph
        NavGraph(
            navController = navController,
            modifier = Modifier.padding(padding),
            viewModel = viewModel
        )
    }
}