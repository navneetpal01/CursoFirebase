package com.example.cursofirebase

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cursofirebase.presentation.nvgraph.NavGraph
import com.example.cursofirebase.presentation.nvgraph.Route
import com.example.cursofirebase.ui.theme.CursoFirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.app

class MainActivity : ComponentActivity() {

//    private lateinit var analytics : FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
//        analytics = Firebase.analytics
        setContent {
            CursoFirebaseTheme {
                NavGraph(
                    startDestination = Route.Login.route,
                    this
                )
            }
        }
    }
}
