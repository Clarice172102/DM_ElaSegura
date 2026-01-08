package com.example.elasegura.ui.navigation

sealed class Route(val route: String) {

    object Splash : Route("splash")
    object Home : Route("home")
    object Perfil : Route("perfil")
    object Mapa : Route("mapa")
    object Contatos : Route("contatos")
    object AddContact : Route("addContact")

    object Register : Route("register")
    object Login : Route("login")
}