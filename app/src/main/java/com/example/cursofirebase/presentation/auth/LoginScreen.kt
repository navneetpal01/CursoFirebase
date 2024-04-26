package com.example.cursofirebase.presentation.auth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.example.cursofirebase.utils.AuthManager
import com.example.cursofirebase.utils.AuthRes
import kotlinx.coroutines.launch

@SuppressLint("InvalidAnalyticsName")
@Composable
fun Login(
    analytics: AnalyticsManager,
    navigation: NavController,
    auth: AuthManager
) {
    analytics.LogScreenView(screenName = Route.Login.route)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
            value = email,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { value ->
                email = value
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { value ->
                password = value
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                          scope.launch {
                              emailPassSignIn(email,password,auth,analytics,context,navigation)
                          }
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
                navigation.navigate(Route.ForgotPassword.route) {
                    popUpTo(Route.Login.route) {
                        inclusive = true
                    }
                }
                analytics.logButtonClicked("Click: Did you forget your password?")
            },
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple40
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "-------- o --------",
            style = TextStyle(
                color = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        SocialMediaButton(
            onClick = {
                scope.launch {
                    incognitoSignIn(auth = auth, analytics = analytics, context = context, navigation = navigation)
                }
            },
            text = "Continue as a guest",
            icon = R.drawable.ic_incognito,
            color = Color(0xFF363636)
        )

        Spacer(modifier = Modifier.height(15.dp))

        SocialMediaButton(
            onClick = {

            },
            text = "Continue with Google",
            icon = R.drawable.ic_google,
            color = Color(0xFFF1F1F1)
        )


    }
}

private suspend fun emailPassSignIn(email: String, password: String, auth: AuthManager, analytics: AnalyticsManager, context: Context, navigation: NavController) {
    if (email.isNotEmpty() && password.isNotEmpty()){
        when(val result = auth.signInWithEmailAndPassword(email,password)){
            is AuthRes.Error -> {
                analytics.logButtonClicked("Error SignUp: ${result.errorMessage}")
                Toast.makeText(context,"Error SignUp: ${result.errorMessage}",Toast.LENGTH_LONG).show()
            }
            is AuthRes.Success -> {
                analytics.logButtonClicked("Click: Login email & password")
                navigation.navigate(Route.Home.route){
                    popUpTo(Route.Login.route){
                        inclusive = true
                    }
                }
            }
        }
    }else{
        Toast.makeText(context,"Empty field exists",Toast.LENGTH_LONG).show()
    }
}


private suspend fun incognitoSignIn(auth: AuthManager, analytics: AnalyticsManager, context: Context, navigation: NavController) {
    when(val result = auth.signInAnonymously()){
        is AuthRes.Error -> {
            analytics.logError("Error SignIn Incognito ${result.errorMessage}")
        }
        is AuthRes.Success -> {
            analytics.logButtonClicked(buttonName = "Click: Continue  as a guest")
            navigation.navigate(Route.Home.route){
                popUpTo(Route.Login.route){
                    inclusive = true
                }
            }
        }
    }
}


@Composable
fun SocialMediaButton(
    onClick: () -> Unit,
    text: String,
    icon: Int,
    color: Color
) {
    var click by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
            .clickable {
                click = !click
            },
        onClick = onClick,
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(
            width = 1.dp,
            color = if (icon == R.drawable.ic_incognito) color else Color.Gray
        ),
        color = color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                painter = painterResource(id = icon),
                contentDescription = "text",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "$text",
                color = if (icon == R.drawable.ic_incognito) Color.White else Color.Black
            )
            click = true
        }
    }
}