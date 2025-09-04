package com.example.jarassignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.jarassignment.presentation.navigation.AppNavHost
import com.example.jarassignment.ui.theme.OnboardingAnimationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnboardingAnimationTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}