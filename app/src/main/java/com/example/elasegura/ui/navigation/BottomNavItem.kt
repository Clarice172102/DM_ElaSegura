package com.example.elasegura.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.elasegura.R
import com.example.elasegura.model.Route
import kotlinx.serialization.Serializable

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val iconRes: Int // o R.drawable.ic_x
) {
    object PerfilButton : BottomNavItem("Perfil", "perfil", R.drawable.user)
    object MapaButton : BottomNavItem("Mapa", "mapa", R.drawable.map)
    object ContatosButton : BottomNavItem("Contatos", "contatos", R.drawable.phone)
}