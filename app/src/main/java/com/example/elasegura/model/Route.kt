package com.example.elasegura.model

sealed class Route(val route: String) {
    data object Splash : Route("splash")
    data object Home : Route("home")
    data object Perfil : Route("perfil")
    data object Map : Route("map")
    data object Contatos : Route("contatos")
}