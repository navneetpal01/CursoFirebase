package com.example.cursofirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cursofirebase.presentation.nvgraph.NavGraph
import com.example.cursofirebase.presentation.nvgraph.Route
import com.example.cursofirebase.presentation.nvgraph.RouteData
import com.example.cursofirebase.presentation.nvgraph.RouteData.Homesec
import com.example.cursofirebase.ui.theme.CursoFirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CursoFirebaseTheme {
                NavGraph(startDestination = Route.Login.route)
            }
        }
    }
}
