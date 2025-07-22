package com.valorantinfo.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.valorantinfo.app.navigation.ValorantNavigation
import com.valorantinfo.app.ui.theme.ValorantInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "MainActivity onCreate called")
        setContent {
            Log.d("MainActivity", "Setting up Compose content")
            ValorantInfoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    ValorantNavigation(navController = navController)
                }
            }
        }
    }
}
