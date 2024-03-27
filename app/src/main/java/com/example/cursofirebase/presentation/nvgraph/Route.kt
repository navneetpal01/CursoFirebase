package com.example.cursofirebase.presentation.nvgraph

sealed class Route(val route : String){
    object Home : Route(route = "HomeScreen")
    object Login : Route(route = "LoginScreen")
}

