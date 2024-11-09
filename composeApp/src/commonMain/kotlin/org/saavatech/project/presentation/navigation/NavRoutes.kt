package org.saavatech.project.presentation.navigation

sealed class NavRoutes(val route: String) {
    data object Loading: NavRoutes("loading")
    data object Login: NavRoutes("Login")
    data object Home: NavRoutes("Home")
    data object Register: NavRoutes("Register")
}