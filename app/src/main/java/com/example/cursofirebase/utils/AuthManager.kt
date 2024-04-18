package com.example.cursofirebase.utils

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await


//Out T is like a seal
sealed class AuthRes<out T> {
    data class Success<T>(val data: T) : AuthRes<T>()
    data class Error(val errorMessage: String) : AuthRes<Nothing>()
}


class AuthManager {
    //using the slow delegation
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    suspend fun signInAnonymously(): AuthRes<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()
            AuthRes.Success(data = result.user ?: throw Exception("Login failed"))
        } catch (e: Exception) {
            AuthRes.Error(errorMessage = e.message ?: "Login failed")
        }
    }

    fun signOut(){
        auth.signOut()
    }

    fun getCurrentUser() : FirebaseUser?{
        return auth.currentUser
    }
    //Let's commit it and sleep for today cause I am f tired now please
}

