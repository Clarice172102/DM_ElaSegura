package com.example.elasegura.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Perfil : Route

    @Serializable
    data object Mapa : Route

    @Serializable
    data object Contatos : Route
}

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: Route
) {

    data object PerfilButton :
        BottomNavItem("Perfil", Icons.Outlined.Person, Route.Perfil)

    data object MapaButton :
        BottomNavItem("Mapa", Icons.Outlined.Map, Route.Mapa)

    data object ContatosButton :
        BottomNavItem("Contatos", Icons.Outlined.Phone, Route.Contatos)
}