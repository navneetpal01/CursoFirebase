package com.example.cursofirebase.presentation.nvgraph

sealed class Route(val route : String){
    object Home : Route(route = "HomeScreen")
    object Login : Route(route = "LoginScreen")
}

sealed class RouteData(val route : String){
    data class Homesec(val routes : String) : RouteData(route = routes)
    data class Loginsec(val routes : String) : RouteData(route = routes)
}