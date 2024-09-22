package com.mhss.app.shifak.presentation.user

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mhss.app.shifak.presentation.assistant.AssistantScreen
import com.mhss.app.shifak.presentation.assistant.AssistantViewModel
import com.mhss.app.shifak.presentation.auth.login.LoginScreenEvent
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.presentation.user.donate_buy.AddDrugEvent
import com.mhss.app.shifak.presentation.user.donate_buy.AddDrugScreen
import com.mhss.app.shifak.presentation.user.donate_buy.AddDrugViewModel
import com.mhss.app.shifak.presentation.user.donate_buy.GetMedicationsEvent
import com.mhss.app.shifak.presentation.user.donate_buy.MedicationsScreen
import com.mhss.app.shifak.presentation.user.donate_buy.MedicationsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.userNestedGraph(navController: NavHostController) {
    navigation<Screen.UserGraph>(
        startDestination = Screen.UserMainScreen
    ) {
        composable<Screen.UserMainScreen>(
            enterTransition = { fadeIn(tween(0)) },
            exitTransition = { fadeOut(tween(0)) },
            popEnterTransition = { fadeIn(tween(0)) },
            popExitTransition = { fadeOut(tween(0)) },
        ) {
            UserMainScreen(navController)
        }
        composable<Screen.AddMedicationScreen> {
            val viewModel = koinViewModel<AddDrugViewModel>()
            AddDrugScreen(
                viewModel.state,
                onEvent = { event ->
                    when (event) {
                        is AddDrugEvent.AddDrug -> viewModel.onEvent(event)
                        AddDrugEvent.NavigateUp -> navController.navigateUp()
                    }
                }
            )
        }
        composable<Screen.MedicationsScreen> {
            val viewModel = koinViewModel<MedicationsViewModel>()
            MedicationsScreen(
                state = viewModel.state,
                onEvent = {
                    when (it) {
                        is GetMedicationsEvent.NavigateUp -> navController.navigateUp()
                    }
                }
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