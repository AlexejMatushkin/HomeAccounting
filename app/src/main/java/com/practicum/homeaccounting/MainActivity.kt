package com.practicum.homeaccounting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.homeaccounting.presentation.navigation.Screens
import com.practicum.homeaccounting.presentation.screens.main.MainScreen
import com.practicum.homeaccounting.presentation.screens.welcome.WelcomeScreen
import com.practicum.homeaccounting.ui.theme.HomeAccountingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeAccountingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeAccountingNavHost()
                }
            }
        }
    }
}

@Composable
fun HomeAccountingNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Welcome.route
    ) {
        composable(Screens.Welcome.route) {
            WelcomeScreen(
                onNavigateToMain = {
                    navController.navigate(Screens.Main.route)
                }
            )
        }

        composable(Screens.Main.route) {
            MainScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
