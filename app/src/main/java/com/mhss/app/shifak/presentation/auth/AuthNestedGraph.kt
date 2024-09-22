package com.mhss.app.shifak.presentation.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.mhss.app.shifak.presentation.auth.login.LoginScreen
import com.mhss.app.shifak.presentation.auth.login.LoginScreenEvent
import com.mhss.app.shifak.presentation.auth.login.LoginViewModel
import com.mhss.app.shifak.presentation.common.Screen
import com.mhss.app.shifak.util.UserType
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.authNestedGraph(navController: NavHostController) {
    navigation<Screen.AuthGraph>(
        startDestination = Screen.AccountTypeScreen
    ) {
        composable<Screen.AccountTypeScreen> {
            AccountTypeScreen(
                onNavigate = { userType ->
                    navController.navigate(Screen.AuthScreen(userType))
                }
            )
        }
        composable<Screen.AuthScreen> { backStackEntry ->
            val userType =
                backStackEntry.toRoute<Screen.AuthScreen>().userType
            AuthScreen(
                onLogin = {
                    navController.navigate(Screen.LoginScreen(userType))
                },
                onSignUp = {
                    navController.navigate(
                        when (userType) {
                            UserType.USER -> Screen.UserSignUpScreen
                            UserType.PHARMACY -> Screen.PharmacySignUpScreen
                        }
                    )
                }
            )
        }
        composable<Screen.LoginScreen> { backStackEntry ->
            val userType =
                backStackEntry.toRoute<Screen.AuthScreen>().userType
            val viewModel = koinViewModel<LoginViewModel>(
                parameters = { parametersOf(userType) }
            )
            LoginScreen(
                state = viewModel.state,
                onEvent = { event ->
                    when (event) {
                        is LoginScreenEvent.Login -> viewModel.onEvent(event)
                        is LoginScreenEvent.Navigate -> navController.navigate(event.screen) {
                            if (event.popUp) popUpTo(Screen.LoginScreen(userType)) {
                                inclusive = true
                            }
                        }
                        LoginScreenEvent.NavigateUp -> navController.navigateUp()
                        LoginScreenEvent.ForgotPassword -> {/* TODO */}
                    }
                }
            )
        }

        composable<Screen.UserSignUpScreen> {
            UserSignUpScreen(
                onSignUpClick = { signUpData ->
                    // Todo
                },
                onLoginInsteadClick = {
                    navController.navigate(Screen.LoginScreen) {
                        popUpTo(Screen.LoginScreen) {
                            inclusive = true
                        }
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable<Screen.PharmacySignUpScreen> {
            PharmacySignUpScreen(
                onSignUpClick = { signUpData ->
                    // Todo
                },
                onLoginInsteadClick = {
                    navController.navigate(Screen.LoginScreen) {
                        popUpTo(Screen.LoginScreen) {
                            inclusive = true
                        }
                    }
                },
                onNavigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}