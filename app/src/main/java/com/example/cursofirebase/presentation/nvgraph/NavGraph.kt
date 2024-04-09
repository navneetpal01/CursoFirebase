package com.example.cursofirebase.presentation.nvgraph

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cursofirebase.presentation.HomeScreen
import com.example.cursofirebase.presentation.Screen
import com.example.cursofirebase.presentation.auth.ForgotPasswordScreen
import com.example.cursofirebase.presentation.auth.Login
import com.example.cursofirebase.presentation.auth.SignUpScreen
import com.example.cursofirebase.utils.AnalyticsManager
import com.example.cursofirebase.utils.AuthManager

@Composable
fun NavGraph(
    startDestination: String,
    context: Context
) {
    val navController = rememberNavController()
    val analytics: AnalyticsManager = AnalyticsManager(context)
    val authManager : AuthManager = AuthManager()
    Screen {
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(route = Route.Login.route) {
                Login(
                    analytics = analytics,
                    navigation = navController,
                    authManager = authManager
                )
            }
            composable(route = Route.Home.route) {
                HomeScreen(
                    analytics = analytics,
                    navigation = navController
                )
            }
            composable(route = Route.SignUp.route) {
                SignUpScreen(
                    analytics = analytics,
                    navigation = navController
                )
            }
            composable(route = Route.ForgotPassword.route) {
                ForgotPasswordScreen(
                    analytics = analytics,
                    navigation = navController
                )
            }
        }
    }
}

//@Composable
//fun TrackScreen(string : String, analytics: FirebaseAnalytics){
//    DisposableEffect(key1 = Unit) {
//        onDispose {
//            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
//                param(FirebaseAnalytics.Param.SCREEN_NAME,string)
//            }
//        }
//    }
//}


















