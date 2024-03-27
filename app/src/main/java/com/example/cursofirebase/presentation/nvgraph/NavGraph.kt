package com.example.cursofirebase.presentation.nvgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cursofirebase.presentation.home.Home
import com.example.cursofirebase.presentation.login.Login

@Composable
fun NavGraph(
    startDestination : String
){
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = startDestination
    ){
        composable(route = Route.Login.route){
            Login(){
                navController.navigate(Route.Home.route)
            }
        }
        composable(route = Route.Home.route){
            Home()
        }
    }
}