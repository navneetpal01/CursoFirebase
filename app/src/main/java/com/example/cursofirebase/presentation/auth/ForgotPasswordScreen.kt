package com.example.cursofirebase.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cursofirebase.presentation.nvgraph.Route
import com.example.cursofirebase.ui.theme.Purple40
import com.example.cursofirebase.utils.AnalyticsManager
import com.example.cursofirebase.utils.AuthManager
import com.example.cursofirebase.utils.AuthRes
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    analytics: AnalyticsManager,
    navigation: NavController,
    authManager: AuthManager
) {
    analytics.LogScreenView(screenName = Route.ForgotPassword.route)

    val context = LocalContext.current
    var email by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Forgot your password",
            style = TextStyle(
                fontSize = 30.sp,
                color = Purple40
            )
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextField(
            label = {
                    Text(
                        text = "Email"
                    )
            },
            value = email,
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            
        )
        
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .padding(40.dp,0.dp,40.dp,0.dp)
        ){
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                onClick = {
                          scope.launch {
                              when(val result = authManager.resetPassword(email)){
                                  is AuthRes.Error -> {
                                      analytics.logError(error = "Reset password error $email : ${result.errorMessage}")
                                      Toast.makeText(context,"Error sending mail",Toast.LENGTH_LONG).show()
                                      navigation.navigate(Route.Login.route)
                                  }
                                  is AuthRes.Success -> {
                                      analytics.logButtonClicked(buttonName = "Reset password $email")
                                      Toast.makeText(context,"Email sent",Toast.LENGTH_LONG).show()
                                      navigation.navigate(Route.Login.route)
                                  }
                              }
                          }
            },
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(text = "Recover Password")
            }
        }
    }

}