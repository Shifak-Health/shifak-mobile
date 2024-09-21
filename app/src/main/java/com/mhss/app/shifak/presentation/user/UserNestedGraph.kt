package com.mhss.app.shifak.presentation.user

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mhss.app.shifak.domain.model.drug.Drug
import com.mhss.app.shifak.domain.model.drug.DrugType
import com.mhss.app.shifak.presentation.assistant.AssistantScreen
import com.mhss.app.shifak.presentation.assistant.AssistantViewModel
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.user.donate_buy.AddDrugScreen
import com.mhss.app.shifak.presentation.user.donate_buy.MedicationsScreen
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.userNestedGraph(navController: NavHostController) {
    navigation<Screen.UserGraph>(
        startDestination = Screen.UserMainScreen
    ) {
        composable<Screen.UserMainScreen> {
            UserMainScreen(navController)
        }
        composable<Screen.AddMedicationScreen> {
            AddDrugScreen(
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
        composable<Screen.MedicationsScreen> {
            MedicationsScreen(
                // TODO
                emptyList(),
               onNavigateUp =  { navController.navigateUp() }
            )
        }
        composable<Screen.SmartAssistantScreen> {
            val viewModel = koinViewModel<AssistantViewModel>()
            val state by viewModel.uiState.collectAsState()
            AssistantScreen(
                state = state,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )
        }
    }
}