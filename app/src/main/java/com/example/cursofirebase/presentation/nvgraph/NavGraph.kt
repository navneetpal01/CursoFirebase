package com.example.cursofirebase.presentation.nvgraph

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cursofirebase.presentation.Screen
import com.example.cursofirebase.presentation.home.Home
import com.example.cursofirebase.presentation.login.Login
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent

@Composable
fun NavGraph(
    startDestination: String,
    analytics: FirebaseAnalytics
) {
    val navController = rememberNavController()
    Screen {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = Route.Login.route) {
                Login(
                    analytics = analytics
                ) {
                    navController.navigate(Route.Home.route)
                }
            }
            composable(route = Route.Home.route) {
                Home(
                    analytics = analytics
                )
            }
        }
    }
}

@Composable
fun TrackScreen(string : String, analytics: FirebaseAnalytics){
    DisposableEffect(key1 = Unit) {
        onDispose {
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
                param(FirebaseAnalytics.Param.SCREEN_NAME,string)
            }
        }
    }
}


















