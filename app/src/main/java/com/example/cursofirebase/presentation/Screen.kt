package com.example.cursofirebase.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cursofirebase.ui.theme.CursoFirebaseTheme

@Composable
fun Screen(
    content : @Composable () -> Unit
){
    CursoFirebaseTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
            content()
        }
    }
}