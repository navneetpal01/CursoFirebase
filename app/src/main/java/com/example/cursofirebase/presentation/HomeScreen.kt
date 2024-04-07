package com.example.cursofirebase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cursofirebase.presentation.nvgraph.Route
import com.example.cursofirebase.utils.AnalyticsManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    analytics: AnalyticsManager,
    navigation: NavController
) {
    analytics.LogScreenView(screenName = Route.Home.route)
    val navController = rememberNavController()
    val onLoggedOutConfirmed: () -> Unit = {

    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            )
        }
    ){
    }
}