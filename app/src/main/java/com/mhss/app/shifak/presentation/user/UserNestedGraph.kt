package com.mhss.app.shifak.presentation.user

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.user.assistant.SmartAssistantScreen
import com.mhss.app.shifak.presentation.user.donate_buy.AddMedicineScreen
import com.mhss.app.shifak.presentation.user.donate_buy.MedicationsScreen

fun NavGraphBuilder.userNestedGraph(navController: NavHostController) {
    navigation<Screen.UserGraph>(
        startDestination = Screen.UserMainScreen
    ) {
        composable<Screen.UserMainScreen> {
            UserMainScreen(navController)
        }
        composable<Screen.AddMedicineScreen> {
            AddMedicineScreen()
        }
        composable<Screen.MedicationsScreen> {
            MedicationsScreen()
        }
        composable<Screen.SmartAssistantScreen> {
            SmartAssistantScreen()
        }
    }
}