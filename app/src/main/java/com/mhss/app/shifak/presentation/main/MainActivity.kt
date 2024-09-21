package com.mhss.app.shifak.presentation.main

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mhss.app.shifak.presentation.auth.authNestedGraph
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.onboarding.OnboardingScreen
import com.mhss.app.shifak.presentation.ui.theme.ShifakTheme
import com.mhss.app.shifak.presentation.user.userNestedGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                scrim = Color.TRANSPARENT,
                darkScrim = Color.BLACK
            )
        )
        setContent {
            ShifakTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.consumeWindowInsets(WindowInsets.navigationBars)
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.OnboardingScreen
                    ) {
                        composable<Screen.OnboardingScreen> {
                            OnboardingScreen(
                                onFinish = {
                                    navController.navigate(Screen.AuthGraph) {
                                        popUpTo(Screen.AuthGraph) {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                        authNestedGraph(navController)
                        userNestedGraph(navController)
                    }
                }
            }
        }
    }
}