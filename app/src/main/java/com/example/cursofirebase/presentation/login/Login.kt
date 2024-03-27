package com.example.cursofirebase.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString

@Composable
fun Login() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        ClickableText(
            text = AnnotatedString("Welcome to the Login Screen")
        ) {

        }
    }
}