package com.example.cursofirebase.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent


class AnalyticsManager(
    context: Context
) {
    //Multiple instances for each contexts
    //lazy only initializes the value when accessed
    private val analyticsManager: FirebaseAnalytics by lazy { FirebaseAnalytics.getInstance(context) }
    //It retrieves the FirebaseAnalytics instance that's associated with that central Firebase configuration
//    private val analyticsManager2 : FirebaseAnalytics by lazy { Firebase.analytics}


//    TODO Can use this function to make the things little easy
//    fun logEvent(string : String, bundle : Bundle){
//        analyticsManager.logEvent(string,bundle)
//    }


    fun logButtonClicked(buttonName: String) {
        val params = Bundle().apply {
//          Putting a Key-Value Pair
            putString("button_name", buttonName)
        }
        analyticsManager.logEvent("button_clicked", params)
    }

    @Composable
    fun LogScreenView(screenName : String) {
        DisposableEffect(key1 = Unit) {
            onDispose {
                val params = Bundle().apply {
                    putString(FirebaseAnalytics.Param.SCREEN_NAME,screenName)
                    putString(FirebaseAnalytics.Param.SCREEN_CLASS,screenName)
                }
                analyticsManager.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW,params)
            }
        }

    }

    @SuppressLint("InvalidAnalyticsName")
    fun logError(error : String){
        val params = Bundle().apply {
            putString("error",error)
        }
        analyticsManager.logEvent("error",params)
    }


}




//TODO - BEFORE
//analytics.logEvent("configuration") {
//    param("value1", "does not have an account")
//    param("value2", "new user registration")
//}