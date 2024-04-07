package com.example.cursofirebase.presentation.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cursofirebase.R
import com.example.cursofirebase.presentation.nvgraph.Route
import com.example.cursofirebase.ui.theme.Purple40
import com.example.cursofirebase.utils.AnalyticsManager

@SuppressLint("InvalidAnalyticsName")
@Composable
fun Login(
    analytics: AnalyticsManager,
    navigation: NavController
) {
    analytics.LogScreenView(screenName = Route.Login.route)

    val email = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ClickableText(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(50.dp),
            text = AnnotatedString("You do have an account? sign up"),
            onClick = {
                navigation.navigate(Route.SignUp.route)
                analytics.logButtonClicked("Click: Don't have an account?Signup")
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(100.dp),
            painter = painterResource(id = R.drawable.ic_firebase),
            contentDescription = "Firebase"
        )
        Text(
            text = "Firebase Login",
            style = TextStyle(
                fontSize = 30.sp,
                color = Purple40
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            label = { Text(text = "Email") },
            value = email.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { value ->
                email.value = value
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { value ->
                password.value = value
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {

                },
                shape = RoundedCornerShape(50.dp),
            ) {
                Text(text = "Login".uppercase())
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ClickableText(
            text = AnnotatedString("Did you forget your password?"),
            onClick = {
                      navigation.navigate(Route.ForgotPassword.route){
                          popUpTo(Route.Login.route){
                              inclusive = true
                          }
                      }
                analytics.logButtonClicked("Click: ")
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )

    }
}