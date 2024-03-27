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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            CursoFirebaseTheme {
                NavGraph(startDestination = Route.Login.route)
            }
        }
    }
}
