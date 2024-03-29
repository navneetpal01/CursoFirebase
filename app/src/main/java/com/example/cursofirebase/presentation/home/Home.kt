package com.example.cursofirebase.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.cursofirebase.presentation.nvgraph.TrackScreen
import com.google.firebase.analytics.FirebaseAnalytics

@Composable
fun Home(
    analytics: FirebaseAnalytics
){
    TrackScreen(string = "Entered Home Screen", analytics = analytics)
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Start",
            fontSize = 40.sp
        )
    }
}