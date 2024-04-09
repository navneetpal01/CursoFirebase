package com.example.cursofirebase.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cursofirebase.presentation.nvgraph.Route
import com.example.cursofirebase.utils.AnalyticsManager
import com.example.cursofirebase.utils.AuthManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    analytics: AnalyticsManager,
    navigation: NavController,
    authManager : AuthManager
) {
    analytics.LogScreenView(screenName = Route.Home.route)
    val navController = rememberNavController()
    var showDialog by remember { mutableStateOf(false) }
    val onLoggedOutConfirmed: () -> Unit = {
        authManager.signOut()
        navigation.navigate(Route.Login.route){
            popUpTo(Route.Home.route){
                inclusive = true
            }
        }
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
                        Column {
                            Text(
                                text = "welcome",
                                fontSize = 20.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "user",
                                fontSize = 12.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(),
                actions = {
                    IconButton(
                        onClick = {
                            showDialog = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "exit"
                        )
                    }
                },
            )
        },
        bottomBar = {

        }
    ) { contentpadding ->
        Box(
            modifier = Modifier.padding(contentpadding)
        ) {
            if (showDialog) {
                LogoutDialog(
                    onConfirmedLogout = {
                        onLoggedOutConfirmed()
                        showDialog = false
                    },
                    onDismiss = {
                        showDialog = false
                    }
                )
            }

        }

    }
}

@Composable
fun LogoutDialog(
    onConfirmedLogout: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Sign off")
        },
        text = {
            Text(text = "Are you sure you want to log out?")
        },
        confirmButton = {
            Button(
                onClick = onConfirmedLogout
            ) {
                Text(text = "Accept")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text(text = "Cancel")
            }
        }

    )
}



















